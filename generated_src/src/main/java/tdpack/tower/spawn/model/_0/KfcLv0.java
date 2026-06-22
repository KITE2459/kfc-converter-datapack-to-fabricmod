package tdpack.tower.spawn.model._0;

import tdpack.generated.KfcGen;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

/** Auto-generated bridge stub for datapack function `tower:spawn/model/0/lv0`
 *  (매크로/미파싱 함수 - 원본 mcfunction 함수 단위 실행으로 폴백). */
public final class KfcLv0 {
    private KfcLv0() { throw new UnsupportedOperationException(); }

    public static void execute(ServerCommandSource source) {
        KfcGen.instantExecuteFunction(source, Identifier.of("tower", "spawn/model/0/lv0"));
    }

    /** mcfunction return 값 전파 - if function / store result 호출용. */
    public static int executeReturn(ServerCommandSource source) {
        return KfcGen.instantExecuteFunctionReturn(source, Identifier.of("tower", "spawn/model/0/lv0"));
    }

    public static void execute(ServerCommandSource source, java.util.Map<String, String> macroArgs) {
        KfcGen.instantExecuteFunction(source, Identifier.of("tower", "spawn/model/0/lv0"), macroArgs);
    }
}
