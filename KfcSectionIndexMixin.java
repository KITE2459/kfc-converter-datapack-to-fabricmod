package __KFC_GROUP__.mixin;

import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import java.util.List;
import net.minecraft.util.collection.TypeFilterableList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * [N1 — 엔티티 섹션 재배치 O(n) 제거] spark 실측 최대 단일 항목.
 *
 * <p><b>문제</b> — 바닐라는 엔티티를 16³ 섹션(EntityTrackingSection)에 담고, 그 컨테이너가
 * {@link TypeFilterableList} 다. 1.21.5 바이트코드 직접 확인:
 * <pre>
 *   public boolean remove(Object o) {
 *       boolean bl = false;
 *       for (Entry&lt;Class&lt;?&gt;, List&lt;T&gt;&gt; e : this.elementsByType.entrySet())
 *           if (e.getKey().isInstance(o)) bl |= e.getValue().remove(o);   // ← 선형 탐색
 *       return bl;
 *   }
 * </pre>
 * 엔티티가 섹션 경계를 넘거나 제거될 때마다 호출되고, 리스트 길이만큼 {@code Entity.equals} 를 돈다.
 * 카트 1대당 디스플레이 파츠 수백 개가 같은 섹션에서 매 틱 움직이면 n 이 수백인 리스트에서
 * 제거가 초당 수만 번 일어난다(16인 주행 실측: 모드 틱의 14.29%).
 *
 * <p><b>기법</b> — 리스트별 (원소 → 인덱스) 항등 색인으로 <b>swap-remove O(1)</b> 전환.
 *
 * <p><b>[중요] 왜 {@code @Redirect} 인가 — 1차 시도의 실패</b><br>
 * 최초 구현은 {@code @Inject(at = @At("HEAD"), cancellable = true)} 로 {@code add}/{@code remove}
 * 전체를 대체했다. 동작은 맞았지만(퍼징 검증 통과) <b>같은 메서드에 주입한 다른 모드의 변환을
 * 통째로 건너뛰는</b> 부작용이 있었다. 실제로 프로파일에서 확인됐다:
 * <pre>
 *   1차 프로파일: TypeFilterableList.localvar$blf000$lithium$add     (24ms)
 *                TypeFilterableList.localvar$blf000$lithium$remove  (16ms)
 *   2차 프로파일: (두 프레임 모두 소멸 — HEAD 취소로 도달 불가)
 * </pre>
 * 그래서 <b>메서드를 취소하지 않고 내부 {@code List.add}/{@code List.remove} 호출만
 * {@code @Redirect} 로 교체</b>한다. 바닐라의 루프·반환 계약과 타 모드의
 * {@code @ModifyVariable} 이 모두 그대로 살아 있고, 바뀌는 것은 리스트 연산 하나뿐이다.
 * {@code @Shadow} 도 불필요해졌다 — 리다이렉트 수신자로 리스트를 직접 받는다.
 *
 * <p><b>범용성</b> — 바닐라 자료구조만 건드리므로 데이터팩과 무관. 엔티티를 많이 움직이는
 * 모든 팩에 효과가 있고, 그렇지 않으면 임계값 미만이라 개입하지 않는다.
 *
 * <p><b>관측 편차</b> — swap-remove 는 <b>섹션 내부 순회 순서</b>를 바꾼다(집합·개수·필터 결과는
 * 동일). 바닐라도 섹션 내부 순서를 보장하지 않고 {@code sort} 미지정 {@code limit=} 은 명세상
 * arbitrary 다. 영향을 줄이기 위해 <b>짧은 리스트(기본 16 미만)는 바닐라 경로 그대로</b> 둔다.
 *
 * <p><b>토글</b> (KfcGen 에 정의) — {@code -Dkfc.sectionidx=off} 전체 비활성 /
 * {@code -Dkfc.sectionidx.min=N} 적용 최소 길이(기본 16).
 *
 * <p><b>fail-safe</b> — 색인과 리스트 길이가 어긋나면 재구축, 항등 조회가 빗나가면 바닐라
 * {@code List.remove(Object)}(equals 기반) 폴백, 중복 원소 발견 시 그 리스트는 영구 바닐라 경로.
 * 어떤 경우에도 반환값과 결과 집합이 바닐라와 동일하다. {@code require = 0} 이라 타깃
 * 호출이 사라져도 크래시 없이 바닐라로 남는다.
 *
 * <p><b>설계 노트</b> — 상수 아닌 static 필드를 두지 않는다(바닐라 클래스에 {@code <clinit>}
 * 병합 유발 방지). 설정값은 {@code KfcGen} 에 있고, '색인 불가' 표식은 별도 센티널 대신
 * <b>리스트 자기 자신</b>을 맵 값으로 넣어 표현한다(추가 할당 0).
 */
@Mixin(TypeFilterableList.class)
public abstract class KfcSectionIndexMixin<T> {

    /** 리스트 객체(identity) → 색인 맵, 또는 리스트 자기 자신(= '색인 불가' 표식).
     *  섹션당 리스트는 소수(대개 1~3)라 맵이 작다. 지연 생성이라 임계값 미만 섹션은
     *  null 참조 필드 1개 외에 오버헤드가 없다. */
    @Unique private Reference2ObjectOpenHashMap<List<T>, Object> kfc$idx;

    /**
     * 해당 리스트의 색인을 얻는다. 없거나 길이가 어긋나면 재구축한다.
     * 색인을 쓰면 안 되는 경우(비활성/소형/중복원소) null 을 반환하고 호출측은 바닐라 경로를 탄다.
     */
    @Unique
    @SuppressWarnings("unchecked")
    private Reference2IntOpenHashMap<Object> kfc$map(List<T> l) {
        final int n = l.size();
        if (n < __KFC_GROUP__.generated.KfcGen.SECTION_IDX_MIN) return null;   // 소형 — 순서 보존
        if (kfc$idx == null) kfc$idx = new Reference2ObjectOpenHashMap<>(4);
        final Object v = kfc$idx.get(l);
        if (v == l) return null;                                  // 중복 원소 리스트 — 영구 폴백
        if (v != null) {
            final Reference2IntOpenHashMap<Object> m = (Reference2IntOpenHashMap<Object>) v;
            if (m.size() == n) return m;                          // 길이 일치 = 정합성 스탬프
        }
        // (재)구축: 최초 접근, getAllOfType 이 지연 생성한 새 리스트, 또는 어떤 이유로든 드리프트.
        final Reference2IntOpenHashMap<Object> m = new Reference2IntOpenHashMap<>(n * 2);
        m.defaultReturnValue(-1);
        for (int i = 0; i < n; i++) {
            if (m.put(l.get(i), i) != -1) {                       // 같은 원소가 두 번 — 색인 표현 불가
                kfc$idx.put(l, l);
                return null;
            }
        }
        kfc$idx.put(l, m);
        return m;
    }

    /**
     * {@code remove} 안의 {@code List.remove(Object)} 만 교체. 바닐라 루프·{@code bl |=} 누산·
     * 타 모드 주입은 그대로 살아 있다. 반환 계약 동일(제거했으면 true).
     */
    @Redirect(method = "remove(Ljava/lang/Object;)Z",
              at = @At(value = "INVOKE",
                       target = "Ljava/util/List;remove(Ljava/lang/Object;)Z", remap = false),
              require = 0)
    private boolean kfc$fastRemove(List<T> list, Object o) {
        if (!__KFC_GROUP__.generated.KfcGen.SECTION_IDX_ON) return list.remove(o);
        final Reference2IntOpenHashMap<Object> m = kfc$map(list);
        if (m == null) return list.remove(o);                     // 소형/중복 — 바닐라 경로
        final int p = m.getInt(o);
        if (p < 0) return list.remove(o);                         // 항등 미스 → equals 로 재확인
        final int last = list.size() - 1;
        if (p != last) {                                          // swap: 꼬리를 빈자리로
            final T moved = list.get(last);
            list.set(p, moved);
            m.put(moved, p);
        }
        list.remove(last);                                        // 꼬리 제거 = O(1)
        m.removeInt(o);
        return true;
    }

    /**
     * {@code add} 안의 {@code List.add(Object)} 만 교체 — 색인 유지 목적.
     * 반환 계약 동일(List.add 결과 그대로).
     */
    @Redirect(method = "add(Ljava/lang/Object;)Z",
              at = @At(value = "INVOKE",
                       target = "Ljava/util/List;add(Ljava/lang/Object;)Z", remap = false),
              require = 0)
    private boolean kfc$fastAdd(List<T> list, T e) {
        if (!__KFC_GROUP__.generated.KfcGen.SECTION_IDX_ON) return list.add(e);
        final Reference2IntOpenHashMap<Object> m = kfc$map(list);
        final int pos = list.size();
        final boolean r = list.add(e);
        if (m != null && m.put(e, pos) != -1) {                   // 중복 삽입 — 색인 포기
            kfc$idx.put(list, list);
        }
        return r;
    }
}
