package __KFC_GROUP__.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * [30차 — 탑승 위상 변이 훅] {@code Entity.addPassenger}/{@code removePassenger} 는 승객 리스트의
 * <b>유일한 변이 지점</b>이다 (1.21.5 확인: {@code passengerList} 는 private ImmutableList 이고
 * 대입은 이 두 메서드에만 존재; {@code removeAllPassengers}→{@code stopRiding}→{@code removePassenger},
 * 풀 텔레포트의 승객 하차·kill·타 모드 전부 이 경로를 지난다). 여기서 전역 세대
 * {@code RIDE_TOPO_GEN} 을 올리면 <b>누가 어떤 경로로 바꾸든</b> 탑승 트리 캐시가 정확히 무효화된다.
 *
 * <p><b>동기(이력)</b> — 27차에 승객 평탄화 캐시를 (틱, ENTITY_GEN, RIDE_MUT) 축으로 시도했다가
 * 원복했다: {@code teleportTo} 의 하차가 세 축 중 무엇도 올리지 않아, 하차한 파츠를 캐시가 계속
 * delta 로 끌고 다녔다(spark: packDegrees 12배 회귀). 이 훅은 축을 '우리가 아는 변이 명령'이
 * 아니라 <b>변이 그 자체</b>에 두므로 그 계열의 누락이 구조적으로 불가능하다.
 *
 * <p><b>자기 부트스트랩 게이트</b> — 훅이 실제 발동하기 전에는 {@code RIDE_HOOK_ACTIVE} 가
 * false 라 KfcGen 이 캐시를 켜지 않고 종전(매회 {@code getPassengersDeep()} 재귀)대로 동작한다.
 * 믹스인이 적용되지 않으면 영원히 종전 경로 — fail-safe ({@code KfcEntityTagMixin} 과 동일 패턴).
 *
 * <p>{@code require = 0}: 미래 MC 에서 타깃 소실 시 크래시 없이 최적화만 소실.
 */
@Mixin(Entity.class)
public abstract class KfcRideMixin {

    @Inject(method = "addPassenger(Lnet/minecraft/entity/Entity;)V", at = @At("RETURN"), require = 0)
    private void kfc$onPassengerAdded(Entity passenger, CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.onRideTopologyChanged();
    }

    @Inject(method = "removePassenger(Lnet/minecraft/entity/Entity;)V", at = @At("RETURN"), require = 0)
    private void kfc$onPassengerRemoved(Entity passenger, CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.onRideTopologyChanged();
    }
}
