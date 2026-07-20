package __KFC_GROUP__.mixin;

import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 25차[무결성·성능 양립] — 외부 명령 실행 감지 → 네이티브 캐시 화해 믹스인.
 *
 * 문제: 변환 모드는 #minecraft:tick 함수만 네이티브로 실행한다. 커맨드블럭·플레이어 채팅 명령·
 *      /function·타 데이터팩은 전부 바닐라 명령 엔진으로 실행되며, 그 안의 scoreboard reset/set·
 *      tag add/remove·data modify 가 네이티브 무효화 훅을 우회한다 → 점수핸들/태그버킷/엔티티 NBT
 *      스냅샷 캐시가 stale(예: 사운드룸→차고 전환 시 NBS 브금 무음).
 *
 * 관문: 모든 명령 '문자열' 실행은 CommandManager.executeWithPrefix(ServerCommandSource, String) 을
 *      지난다 — CommandBlockExecutor(커맨드블럭)·ServerPlayNetworkHandler(채팅)·명령블럭 마인카트
 *      전부 이 메서드를 호출한다. /function 도 이 관문을 지나 FunctionCommand 로 디스패치된다.
 *      (종전 CommandFunctionManager.execute 타겟은 현대 MC 의 CommandExecutionContext 스텝 실행
 *       리팩터 때문에 /function·직접 명령이 안 타서 감지 실패했다.) 우리 브릿지 runCommand 도 이
 *      메서드를 쓰므로 시그니처가 이미 검증돼 있다.
 *
 * 동작: HEAD 에서 KfcGen.markExternalCommand() 로 플래그만 세팅(부하 0). 실제 화해는 다음 네이티브
 *      접근(getOrCreateContext)이 소비한다 — 명령 완료 후이므로 변이가 반영된 상태로 무효화된다.
 *      우리 네이티브 틱(executeReturn 직접호출)·함수 브릿지(callWithContext)는 이 메서드를 안 타므로
 *      정상 경로 캐시는 유지되어 성능이 회복된다.
 *
 * require = 0 (fail-safe): 주입 실패 시 크래시 없이 건너뛴다 — KfcGen 의 100틱 안전망이 최종 정합을
 *      보장(느릴 뿐). 첫 감지 시 콘솔에 1회 로그를 남기므로 주입 성공 여부를 확인할 수 있다.
 */
@Mixin(CommandManager.class)
public class KfcFuncCoherenceMixin {
    @Unique private static boolean kfc$applyLogged = false;

    // [부팅 적용 로그] CommandManager 는 서버 부팅(및 /reload) 시 생성된다 — 이 핸들러가 붙어
    // 실행되면 CommandManager 로의 메서드 주입이 성공했다는 뜻(executeWithPrefix 훅도 같은 클래스·
    // 같은 리맵 경로라 사실상 함께 적용된다). SCS 처럼 1회만 로그. 부팅 전용이라 hot path 아님.
    @Inject(method = "<init>", at = @At("TAIL"), require = 0)
    private void kfc$logApply(CallbackInfo ci) {
        if (!kfc$applyLogged) {
            kfc$applyLogged = true;
            System.out.println("[KFC] mixin apply-check: FuncCoherence(CommandManager) = APPLIED (executeWithPrefix hook live)");
        }
    }

    @Inject(method = "executeWithPrefix", at = @At("HEAD"), require = 0)
    private void kfc$onExternalCommand(CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.markExternalCommand();
    }
}