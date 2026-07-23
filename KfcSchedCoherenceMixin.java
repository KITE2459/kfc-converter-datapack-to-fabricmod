package __KFC_GROUP__.mixin;

import net.minecraft.server.function.CommandFunctionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * [P1 동반 — 스케줄 함수 화해 훅] /schedule 로 지연 실행되는 함수는 CommandManager.execute 를
 * 타지 않는다: 바닐라 월드 타이머 → FunctionTimerCallback/FunctionTagTimerCallback →
 * CommandFunctionManager.execute(CommandFunction, ServerCommandSource) 직행(1.21.5 상수풀 확인).
 * 이 관문 HEAD 에서 dirty-flag 를 세팅해, 지연 실행된 함수의 scoreboard/tag/data 변이도
 * FuncCoherence 와 동일하게 다음 네이티브 접근에서 즉시 화해되게 한다.
 *
 * 이 훅이 있어야 -Dkfc.reconticks 로 주기 안전망을 완화(100→1200틱)해도 /schedule 경유
 * 변이가 최대 60초 stale 로 남는 갭이 생기지 않는다(안전망 완화의 전제 조건).
 *
 * 우리 네이티브 스케줄(scheduleNative)·브릿지(callWithContext)는 이 메서드를 안 타므로
 * 정상 경로 캐시는 유지된다. /function 명령이 이 관문을 겹쳐 타더라도 dirty 세팅은 멱등.
 * require = 0 (fail-safe): 주입 실패 시 크래시 없이 스킵 — 주기 안전망이 최종 정합 보장.
 */
@Mixin(CommandFunctionManager.class)
public class KfcSchedCoherenceMixin {
    @Inject(method = "execute(Lnet/minecraft/server/function/CommandFunction;Lnet/minecraft/server/command/ServerCommandSource;)V",
            at = @At("HEAD"), require = 0)
    private void kfc$onFunctionExecute(CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.markExternalFunction();
    }
}
