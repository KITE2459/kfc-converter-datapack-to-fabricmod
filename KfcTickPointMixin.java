package __KFC_GROUP__.mixin;

import net.minecraft.server.function.CommandFunctionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 변환된 tick 태그 함수의 <b>실행 시점</b>을 바닐라와 일치시킨다.
 *
 * <p><b>[문제]</b> 종전 진입점은 Fabric 의 {@code ServerTickEvents.START_SERVER_TICK} 으로
 * tick 함수를 돌렸다. 그런데 Fabric 은 이 이벤트를 {@code MinecraftServer.tick} 안의
 * {@code tickWorlds(...)} <b>호출 직전</b>에 주입한다(fabric-lifecycle-events 소스 확인).
 * 반면 바닐라의 {@code #minecraft:tick} 함수는 {@code tickWorlds} <b>내부</b>에서 돈다:
 *
 * <pre>
 * protected void tickWorlds(...) {
 *     getPlayerManager().getPlayerList().forEach(p -> p.networkHandler.disableFlush());  // ①
 *     profiler.push("commandFunctions");
 *     getCommandFunctionManager().tick();          // ← 바닐라 #tick 함수 실행 지점
 *     ... 각 월드 tick ...
 *     serverPlayerEntity.networkHandler.enableFlush();                                   // ②
 * }
 * </pre>
 *
 * <p>즉 바닐라의 tick 함수가 만든 패킷은 ①~② 의 <b>flush 억제 구간</b> 안에서 생성되어,
 * 같은 틱의 엔티티 위치 동기화 패킷과 <b>한 배치로</b> 클라이언트에 전달된다. 종전 시점은
 * ① 이전이라 tick 함수가 만든 패킷(playsound/particle/엔티티 데이터)이 <b>개별 flush</b> 되어
 * 위치 동기화보다 한 배치 먼저 도착했다. 정지 상태에선 차이가 없지만, 고속 이동 중에는
 * 클라이언트가 <b>이전 틱 위치</b>를 기준으로 사운드를 공간화하게 되어 소리가 감쇠·끊겨
 * 들린다(속도가 빠를수록 악화 — 대포/부스터 구간에서 BGM·엔진음이 작게 씹히던 증상).
 *
 * <p><b>[해결]</b> {@code CommandFunctionManager.tick()} 의 TAIL 에 주입해 바닐라가 tick 태그
 * 함수를 돌리는 바로 그 자리에서 변환 함수를 디스패치한다. 남아 있는(미변환) 데이터팩 tick
 * 함수가 있으면 그 뒤에 이어 실행되므로 태그 순서 의미도 보존된다.
 *
 * <p><b>[동결 정합]</b> 바닐라 {@code tick()} 은 {@code tickManager.shouldTick()} 이 거짓이면
 * (/tick freeze 등) tick 함수를 아예 돌리지 않는다. TAIL 주입은 그 분기 밖이므로 훅 쪽에서
 * 동일 조건을 다시 확인한다({@code KfcGen.runTickHook}).
 */
@Mixin(CommandFunctionManager.class)
public abstract class KfcTickPointMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void kfc$runConvertedTickFunctions(CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.runTickHook();
    }
}
