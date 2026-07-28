package __KFC_GROUP__.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.function.CommandFunctionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 변환된 tick 태그 함수를 <b>바닐라와 같은 지점</b>에서 디스패치한다.
 *
 * <p>바닐라의 {@code #minecraft:tick} 함수는 {@code MinecraftServer.tickWorlds} 안에서 돈다:
 *
 * <pre>
 * protected void tickWorlds(...) {
 *     getPlayerManager().getPlayerList().forEach(p -> p.networkHandler.disableFlush());  // ①
 *     getCommandFunctionManager().tick();          // ← 여기 (이 믹스인의 주입 대상)
 *     ... 각 월드 tick ...
 *     ... networkHandler.enableFlush();                                                  // ②
 * }
 * </pre>
 *
 * <p>①~② 는 flush 억제 구간이라, 이 자리에서 만든 패킷은 같은 틱의 엔티티 위치 동기화와
 * <b>한 배치로</b> 전달된다. Fabric 의 {@code START_SERVER_TICK} 은 {@code tickWorlds} 호출
 * 직전(① 이전)이라 그 배치 밖이다.
 *
 * <p><b>이 파일은 변환 시 tick 지점이 'function'(기본)일 때만 산출물에 포함된다.</b>
 * {@code --onserverstart} 로 변환하면 진입점이 {@code START_SERVER_TICK} 에 직접 등록하고
 * 이 믹스인은 아예 복사되지 않는다 — 매 틱 무의미한 분기가 남지 않도록 하기 위함이다.
 *
 * <p><b>[동결 정합]</b> 바닐라 {@code tick()} 은 {@code tickManager.shouldTick()} 이 거짓이면
 * (/tick freeze 등) tick 함수를 돌리지 않는다. TAIL 주입은 그 분기 밖이라 훅에서 같은 조건을
 * 확인한다({@code KfcGen.runTickHook}).
 *
 * <p><b>[주입 확인]</b> 생성자 TAIL 에서 {@code markTickPointApplied()} 를 호출한다. 진입점이
 * SERVER_STARTED 에서 1회 확인해, 주입 실패 시 조용히 아무것도 안 도는 상태를 즉시 알린다.
 */
@Mixin(CommandFunctionManager.class)
public abstract class KfcTickPointMixin {

    @Shadow @Final private MinecraftServer server;

    /** 주입 성공 신호 — 서버 부팅(및 /reload) 시 CommandFunctionManager 가 생성될 때 1회. */
    @Inject(method = "<init>", at = @At("TAIL"), require = 0)
    private void kfc$markApplied(CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.markTickPointApplied();
    }

    @Inject(method = "tick", at = @At("TAIL"), require = 0)
    private void kfc$runConvertedTickFunctions(CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.runTickHook(this.server);
    }
}
