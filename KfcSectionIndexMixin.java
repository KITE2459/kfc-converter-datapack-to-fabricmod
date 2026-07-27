package __KFC_GROUP__.mixin;

import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.util.collection.TypeFilterableList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * [N1 — 엔티티 섹션 재배치 O(n) 제거] spark 실측 최대 단일 항목.
 *
 * <p><b>문제</b> — 바닐라는 엔티티를 16³ 섹션(EntityTrackingSection)에 담고, 그 컨테이너가
 * {@link TypeFilterableList} 다. 1.21.5 바이트코드 직접 확인:
 * <pre>
 *   public boolean remove(Object o) {
 *       boolean bl = false;
 *       for (Entry&lt;Class&lt;?&gt;, List&lt;T&gt;&gt; e : this.elementsByType.entrySet())
 *           if (e.getKey().isInstance(o)) bl |= e.getValue().remove(o);   // ← List.remove(Object) = 선형 탐색
 *       return bl;
 *   }
 * </pre>
 * 엔티티가 섹션 경계를 넘거나 제거될 때마다 SectionedEntityCache → EntityTrackingSection →
 * 이 remove 가 호출되고, 리스트 길이만큼 {@code Entity.equals} 를 돈다.
 *
 * <p><b>실측(16인 주행 spark, Server thread)</b> — 이 모드 tick 서브트리(서버 틱의 65.7%) 기준:
 * <pre>
 *   7.39% (1,852ms)  Entity.equals ← Objects.equals ← ObjectArrayList.indexOf
 *                    ← ObjectArrayList.remove ← TypeFilterableList.remove
 *   2.35% (  588ms)  ObjectArrayList.indexOf ← ... ← SectionedEntityCache.method_31767
 *   ─ 합계 14.29% (3,580ms). 모드 틱의 1/7.
 * </pre>
 * 데이터팩이 카트 1대당 디스플레이 파츠 수백 개를 <b>같은 섹션에 몰아넣고 매 틱 이동</b>시키므로
 * n 이 수백인 리스트에서 제거가 초당 수만 번 일어난다. 바닐라 맵에서 증상이 없는 이유이기도 하다.
 *
 * <p><b>기법</b> — 리스트별 (원소 → 인덱스) 항등 색인을 붙여 <b>swap-remove O(1)</b> 로 전환한다.
 * {@code KfcPerfMixin} 의 승객 인덱스와 동일한 패턴이다.
 *
 * <p><b>범용성</b> — 바닐라 자료구조만 건드리므로 데이터팩과 무관하게 동작한다. 엔티티를 많이
 * 움직이는 모든 팩에 효과가 있고, 그렇지 않은 팩에서는 임계값 미만이라 아예 개입하지 않는다.
 *
 * <p><b>관측 편차(문서화)</b> — swap-remove 는 <b>섹션 내부 순회 순서</b>를 바꾼다. 따라서
 * {@code @e} 셀렉터가 같은 집합을 다른 순서로 돌 수 있다(집합·개수·필터 결과는 완전히 동일).
 * 바닐라도 섹션 내부 순서를 보장하지 않으며(엔티티가 섹션을 오갈 때마다 뒤로 재삽입된다)
 * {@code sort} 미지정 {@code limit=} 은 명세상 arbitrary 다. 그럼에도 영향을 줄이기 위해
 * <b>짧은 리스트(기본 16 미만)는 바닐라 경로 그대로</b> 두어 순서를 보존한다 — 문제가 되는 건
 * 대형 리스트뿐이고, 소형은 선형 탐색이 색인보다 빠르기도 하다.
 *
 * <p><b>토글</b> (KfcGen 에 정의)
 * <ul>
 *   <li>{@code -Dkfc.sectionidx=off} — 전체 비활성(바닐라 원복)
 *   <li>{@code -Dkfc.sectionidx.min=N} — 색인 적용 최소 길이(기본 16)
 * </ul>
 *
 * <p><b>fail-safe</b> — 색인과 리스트 길이가 어긋나면 즉시 재구축하고, 항등 조회가 빗나가면
 * 바닐라 {@code List.remove(Object)}(equals 기반)로 폴백하며, 중복 원소가 발견되면 그 리스트는
 * 영구히 바닐라 경로로 되돌린다. <b>어떤 경우에도 결과 집합은 바닐라와 동일하다.</b>
 * {@code require = 0} 이라 미래 MC 에서 타깃이 사라져도 크래시 없이 바닐라로 남는다.
 *
 * <p><b>타 모드 공존</b> — Lithium 은 이 클래스의 {@code getAllOfType} 과 내부 컬렉션 구현만
 * 바꾼다(실측 프로파일에서 {@code ObjectArrayList} 로 관측 — 즉 {@code remove} 는 바닐라 로직
 * 그대로다). {@code add}/{@code remove} 를 건드리지 않으므로 충돌하지 않고, {@code @Shadow} 도
 * 필드 <i>디스크립터</i>(Map/List) 기준이라 구현 클래스가 교체돼도 유효하다.
 *
 * <p><b>설계 노트</b> — 이 믹스인에는 상수 아닌 static 필드가 없다. 바닐라 클래스에 {@code <clinit>}
 * 병합을 유발하지 않기 위해 설정값은 {@code KfcGen} 에 두고, '색인 불가' 표식은 별도 센티널 객체
 * 대신 <b>리스트 자기 자신</b>을 맵 값으로 넣어 표현한다(추가 할당 0).
 */
@Mixin(TypeFilterableList.class)
public abstract class KfcSectionIndexMixin<T> {

    @Shadow @Final private Map<Class<?>, List<T>> elementsByType;

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
        if (n < __KFC_GROUP__.generated.KfcGen.SECTION_IDX_MIN) return null;  // 소형 — 순서 보존
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
     * 바닐라 add 재현 + 색인 유지. 반환 계약 동일: 타입이 일치하는 엔트리가 하나라도 있으면
     * true(List.add 는 항상 true), 없으면 false.
     */
    @Inject(method = "add(Ljava/lang/Object;)Z", at = @At("HEAD"), cancellable = true, require = 0)
    private void kfc$addIndexed(T e, CallbackInfoReturnable<Boolean> cir) {
        if (!__KFC_GROUP__.generated.KfcGen.SECTION_IDX_ON) return;
        boolean changed = false;
        for (Map.Entry<Class<?>, List<T>> en : this.elementsByType.entrySet()) {
            if (!en.getKey().isInstance(e)) continue;
            final List<T> l = en.getValue();
            final Reference2IntOpenHashMap<Object> m = kfc$map(l);
            final int pos = l.size();
            changed |= l.add(e);
            if (m != null && m.put(e, pos) != -1) {               // 중복 삽입 — 색인 포기
                kfc$idx.put(l, l);
            }
        }
        cir.setReturnValue(changed);
    }

    /**
     * 바닐라 remove 재현 + O(1) swap-remove. 반환 계약 동일(하나라도 지웠으면 true).
     * 색인이 없거나(소형/중복) 항등 조회가 빗나가면 바닐라 {@code List.remove(Object)} 로
     * 폴백하므로 결과는 어떤 경우에도 바닐라와 동일하다.
     */
    @Inject(method = "remove(Ljava/lang/Object;)Z", at = @At("HEAD"), cancellable = true, require = 0)
    private void kfc$removeIndexed(Object o, CallbackInfoReturnable<Boolean> cir) {
        if (!__KFC_GROUP__.generated.KfcGen.SECTION_IDX_ON) return;
        boolean changed = false;
        for (Map.Entry<Class<?>, List<T>> en : this.elementsByType.entrySet()) {
            if (!en.getKey().isInstance(o)) continue;
            final List<T> l = en.getValue();
            final Reference2IntOpenHashMap<Object> m = kfc$map(l);
            if (m == null) { changed |= l.remove(o); continue; }          // 폴백(관측 동일)

            final int p = m.getInt(o);
            if (p < 0) { changed |= l.remove(o); continue; }              // 항등 미스 → equals 재확인
            final int last = l.size() - 1;
            if (p != last) {                                              // swap: 꼬리를 빈자리로
                final T moved = l.get(last);
                l.set(p, moved);
                m.put(moved, p);
            }
            l.remove(last);                                               // 꼬리 제거 = O(1)
            m.removeInt(o);
            changed = true;
        }
        cir.setReturnValue(changed);
    }
}