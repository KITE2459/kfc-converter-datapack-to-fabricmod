package __KFC_GROUP__.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * [N4 — 트래커 추적거리 승객 트리 순회 제거] spark 실측 최대 단일 항목.
 *
 * <p><b>문제</b> — {@code ServerChunkLoadingManager$EntityTracker.getMaxTrackDistance()} 는
 * 1.21.5 바이트코드 직접 확인 결과 다음과 같다:
 * <pre>
 *   private int getMaxTrackDistance() {
 *       int i = this.maxDistance;
 *       for (Entity e : this.entity.getPassengersDeep())            // ← 승객 트리 전체 순회
 *           i = Math.max(i, e.getType().getMaxTrackDistance() * 16);
 *       return this.adjustTrackingDistance(i);
 *   }
 * </pre>
 * 호출부가 {@code updateTrackedStatus(ServerPlayerEntity)} 이므로 <b>추적 엔티티마다 ×
 * 플레이어마다 × 매 틱</b> 이 트리를 다시 걷는다. {@code getPassengersDeep()} 는 호출할 때마다
 * 지연 concat Iterable 을 새로 만든다. 카트 1대에 디스플레이 파츠 수백 개가 승객으로 달리면
 * 비용이 (파츠 수 × 플레이어 수 × 카트 수) 곱셈으로 자란다.
 *
 * <p>spark 125초(실작업 15.22 ms/tick 기준):
 * <pre>
 *   ServerChunkLoadingManager.tickEntityMovement        20.73%   3.155 ms/tick
 *     EntityTracker.updateTrackedStatus(player)         14.64%   2.227
 *       EntityTracker.getMaxTrackDistance                8.57%   1.304   ← 이 믹스인의 대상
 *         Entity.getPassengersDeep                       5.32%   0.810
 *           Entity.addPassengersDeep                     6.23%   0.947
 * </pre>
 *
 * <p><b>기법</b> — 루프가 구하는 값은 '승객 타입 추적거리의 최댓값' <b>하나</b>뿐이다.
 * {@code *16} 은 단조 증가라 raw 추적거리의 argmax 가 곱한 뒤의 argmax 와 같다. 따라서
 * <b>최댓값을 내는 승객 1개만 담은 리스트</b>를 순회시켜도 {@code i} 는 완전히 동일하고,
 * 루프가 O(승객 수) → O(1) 이 된다. 승객이 없으면 빈 리스트({@code List.of()})로 충분하다.
 *
 * <p><b>[중요] 왜 {@code @Redirect} 인가 — 메서드를 취소하지 않는다</b><br>
 * {@code @Inject(HEAD, cancellable)} 로 전체를 대체하면 {@code adjustTrackingDistance} 를 직접
 * 재현해야 하는데, 그 값은 <b>캐시하면 안 되는 라이브 설정</b>이다(바이트코드 확인):
 * <ul>
 *   <li>전용서버: {@code getProperties().entityBroadcastRangePercentage * i / 100}</li>
 *   <li>통합서버: {@code options.getEntityDistanceScaling() * i}</li>
 * </ul>
 * 게다가 HEAD 취소는 같은 메서드에 주입한 다른 모드(예: entity-view-distance 계열)의 변환을
 * 통째로 건너뛴다 — {@code KfcSectionIndexMixin} 이 lithium 프레임을 소멸시켰던 것과 같은 부류의
 * 사고다. 그래서 <b>내부 {@code getPassengersDeep()} 호출 하나만</b> 리다이렉트한다. 바닐라의
 * 루프·클램프·반환 계약과 타 모드의 주입이 전부 그대로 살아 있고, 바뀌는 것은 '무엇을 순회하는가'
 * 뿐이다.
 *
 * <p><b>무효화</b> — 30차 {@code KfcRideMixin} 이 {@code addPassenger}/{@code removePassenger}
 * RETURN 에서 올리는 {@code RIDE_TOPO_GEN} 을 그대로 쓴다. 승객 리스트({@code passengerList},
 * private ImmutableList)의 <b>유일한 변이 지점</b>이라 어떤 경로(풀 텔레포트 하차·kill·타 모드)든
 * 누락이 구조적으로 불가능하고, 이미 승객 평탄화 캐시가 같은 축으로 검증돼 있다. 엔티티 타입은
 * 런타임 불변이므로 트리 구성이 그대로면 최댓값도 그대로다.
 *
 * <p><b>범용성</b> — 바닐라 자료구조만 건드리므로 데이터팩과 무관하다. 승객을 많이 태우는 모든
 * 팩(디스플레이 리그·탈것·모델)에 효과가 있고, 승객이 없으면 캐시 조회 1회로 끝나 손해가 없다.
 * <b>서버 사이드 전용</b> — 패킷·프로토콜·클라이언트 동작은 일절 바뀌지 않는다. 바닐라 클라이언트가
 * 관측하는 추적 거리는 종전과 동일하다.
 *
 * <p><b>fail-safe</b> — {@code RIDE_HOOK_ACTIVE} 가 아직 false 면(훅 미발동·믹스인 미적용) 캐시를
 * 쓰지 않고 원본 {@code getPassengersDeep()} 을 그대로 돌려준다. {@code require = 0} 이라 미래
 * MC 에서 타깃이 사라져도 크래시 없이 바닐라 경로로 남는다(최적화만 소실).
 * {@code -Dkfc.trackdist=off} 로 즉시 원복.
 *
 * <p><b>설계 노트</b> — 대표 승객 참조를 트래커에 들고 있게 되지만, 하차는 곧 RIDE_TOPO_GEN 을
 * 올리고 이 메서드는 매 틱 호출되므로 스테일 참조 보유 구간은 1틱 미만이다. 트래커 자체가
 * 추적 종료 시 폐기되므로 누수 경로가 없다.
 */
@Mixin(targets = "net.minecraft.server.world.ServerChunkLoadingManager$EntityTracker")
public abstract class KfcTrackDistMixin {

    /** 마지막으로 계산한 시점의 탑승 위상 세대. 0 = 미계산(RIDE_TOPO_GEN 은 1부터 시작). */
    @Unique private long kfc$tdStamp = 0L;
    /** 승객 중 타입 추적거리가 최대인 대표 1개. null = 승객 없음(또는 계산 결과 대표 없음). */
    @Unique private Entity kfc$tdRep = null;

    @Redirect(method = "getMaxTrackDistance()I",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/entity/Entity;getPassengersDeep()Ljava/lang/Iterable;"),
              require = 0)
    private Iterable<Entity> kfc$deepForMaxTrack(Entity vehicle) {
        if (!__KFC_GROUP__.generated.KfcGen.trackDistCacheUsable()) {
            return vehicle.getPassengersDeep();      // 훅 미발동/토글 off — 종전 경로 그대로
        }
        long gen = __KFC_GROUP__.generated.KfcGen.rideTopoGen();
        boolean hit = (this.kfc$tdStamp == gen);
        if (!hit) {
            Entity best = null;
            int bestD = Integer.MIN_VALUE;
            for (Entity p : vehicle.getPassengersDeep()) {
                int dd = p.getType().getMaxTrackDistance();
                if (dd > bestD) { bestD = dd; best = p; }
            }
            this.kfc$tdRep = best;
            this.kfc$tdStamp = gen;
        }
        __KFC_GROUP__.generated.KfcGen.markTrackDistApplied(hit);
        Entity r = this.kfc$tdRep;
        // 대표 1개만 순회시켜도 max 결과가 동일(*16 은 단조). 승객 없음이면 루프 자체를 생략.
        return r == null ? java.util.List.of() : java.util.List.of(r);
    }
}