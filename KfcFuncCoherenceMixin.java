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
 * 관문(1.21.5 실측 — 상수풀/매핑 직접 확인):
 *      · 모든 명령의 공통 실행부는 CommandManager.execute(ParseResults, String) [method_9249] 이다.
 *        - 플레이어 채팅 명령: ServerPlayNetworkHandler 가 이 execute 를 '직접' 호출한다
 *          (executeWithPrefix 를 안 탄다 — 종전 가정이 틀렸던 지점. 그래서 플레이어 /명령이 감지 안 됐다).
 *        - 커맨드블럭/콘솔/사인: CommandManager.executeWithPrefix(ServerCommandSource, String)
 *          [method_44252] 를 지나 내부에서 execute(ParseResults, String) 로 위임된다.
 *      · 따라서 execute(ParseResults, String) HEAD 에 걸면 플레이어+커맨드블럭+콘솔+/function 이
 *        전부 잡힌다. executeWithPrefix 는 위임하지 않는 경로 대비 '안전망'으로 함께 건다(이중 발동은
 *        dirty 플래그가 멱등이라 무해). execute 는 오버로드라 @Inject 에 full descriptor 필수.
 *      · 우리 네이티브 틱(executeReturn 직접호출)·함수 브릿지(callWithContext)는 execute 를 안 타므로
 *        정상 경로 캐시는 유지되어 성능이 회복된다. (우리 브릿지 runCommand 는 executeWithPrefix→execute
 *        를 타므로 이 관문에 함께 잡힌다.)
 *
 * 동작: HEAD 에서 KfcGen.markExternalCommand(command) 로 플래그만 세팅(부하 0). 실제 화해는 다음
 *      네이티브 접근(getOrCreateContext)이 소비한다 — 명령 완료 후이므로 변이가 반영된 상태로 무효화된다.
 *
 * require = 0 (fail-safe): 주입 실패 시 크래시 없이 건너뛴다 — KfcGen 의 100틱 안전망이 최종 정합을
 *      보장(느릴 뿐). 부팅 시 <init> 훅이 'APPLIED' 로그를, 실행 시 -Dkfc.debug.coherence=true 가
 *      발동 로그를 남겨 주입/발동을 각각 확인할 수 있다.
 */
@Mixin(CommandManager.class)
public class KfcFuncCoherenceMixin {
    @Unique private static boolean kfc$applyLogged = false;

    // [부팅 적용 로그] CommandManager 는 서버 부팅(및 /reload) 시 생성된다 — 이 핸들러가 붙어
    // 실행되면 CommandManager 로의 메서드 주입 자체가 성공했다는 뜻. SCS 처럼 1회만 로그.
    @Inject(method = "<init>", at = @At("TAIL"), require = 0)
    private void kfc$logApply(CallbackInfo ci) {
        if (!kfc$applyLogged) {
            kfc$applyLogged = true;
            System.out.println("[KFC] mixin apply-check: FuncCoherence(CommandManager) = APPLIED (execute hooks installed)");
        }
    }

    // 공통 실행부 — 플레이어 채팅 명령 포함 모든 명령이 여기로 수렴. 오버로드라 full descriptor 필수.
    @Inject(method = "execute(Lcom/mojang/brigadier/ParseResults;Ljava/lang/String;)V",
            at = @At("HEAD"), require = 0)
    private void kfc$onExecute(com.mojang.brigadier.ParseResults<?> parseResults,
                               String command, CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.markExternalCommand(command);
    }

    // 안전망 — 커맨드블럭/콘솔/사인 경로(executeWithPrefix). execute 로 위임되면 중복이나 멱등이라 무해.
    @Inject(method = "executeWithPrefix", at = @At("HEAD"), require = 0)
    private void kfc$onExternalCommand(net.minecraft.server.command.ServerCommandSource source,
                                       String command, CallbackInfo ci) {
        __KFC_GROUP__.generated.KfcGen.markExternalCommand(command);
    }
}