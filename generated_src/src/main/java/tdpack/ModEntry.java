package tdpack;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.command.ServerCommandSource;

/**
 * 자동 생성 모드 진입점.
 *  - tick 태그 함수 -> 매 서버 틱 (#minecraft:tags/function/tick 대응)
 *  - load 태그 함수 1개 -> 데이터팩 load 태그가 담당(자바 진입점 미관여)
 * 명령 컨텍스트는 서버 커맨드 소스(level 4, silent)를 사용한다.
 */
public final class ModEntry implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            ServerCommandSource src = server.getCommandSource().withSilent();
            tdpack.buckets.Bucket7._m_1628f07b_minecraft_tick_execute(src);
        });
    }
}
