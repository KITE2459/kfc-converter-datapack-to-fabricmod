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
 *
 * <p><b>[개정 — 함수 id 전달]</b> 종전에는 인자를 캡처하지 않고 {@code markExternalFunction()} 를
 * 무인자로 호출했다. 어떤 함수인지 알 수 없으니 {@code EXTERNAL_MASK |= BR_ALL}(fail-closed)
 * 이었고, 그 결과 <b>지연 함수가 매 틱 하나만 발화해도 태그 버킷·스냅샷·타입 인덱스가 통째로
 * 폐기</b>됐다. 실측 로그(16인 주행):
 * <pre>
 *   [KFC-TAGBUCKET] 100틱: 재구축 100 (틱당 1.00) 사유 gen=100  스캔엔티티누계=250,400
 *   [KFC-TAGBUCKET]   gen 유발자: bridgeReconcile=100 | 명령: schedfn(...)x99
 * </pre>
 * 즉 <b>틱당 1회 전체 재구축(엔티티 2,504개 전수 재스캔)의 단일 원인</b>이었다.
 *
 * <p>이제 {@code CommandFunction} 인자를 캡처해 id 를 넘긴다. {@code KfcGen} 이 기존
 * {@code fnMask(fid)}(로드된 mcfunction 원문을 ResourceManager 로 읽어 명령별로 분류하는
 * 런타임 분석기 — {@code /function} 경로에 이미 쓰이던 것)로 <b>실제로 필요한 축만</b> 무효화한다.
 * 함수 본문이 전부 개체군-불변 명령(particle/playsound/title/…)이면 마스크가 0 이 되어
 * <b>무효화 자체가 일어나지 않는다.</b> 자원 부재·예외·서버 미준비는 종전대로 BR_ALL(fail-closed).
 */
@Mixin(CommandFunctionManager.class)
public class KfcSchedCoherenceMixin {
    @Inject(method = "execute(Lnet/minecraft/server/function/CommandFunction;Lnet/minecraft/server/command/ServerCommandSource;)V",
            at = @At("HEAD"), require = 0)
    private void kfc$onFunctionExecute(
            net.minecraft.server.function.CommandFunction<net.minecraft.server.command.ServerCommandSource> function,
            net.minecraft.server.command.ServerCommandSource source,
            CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.markExternalFunction(function == null ? null : function.id());
    }
}
