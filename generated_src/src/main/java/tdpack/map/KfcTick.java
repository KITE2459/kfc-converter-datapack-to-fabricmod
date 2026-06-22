package tdpack.map;

import tdpack.generated.KfcGen;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

/** Auto-generated bridge stub for datapack function `map:tick`
 *  (매크로/미파싱 함수 - 원본 mcfunction 함수 단위 실행으로 폴백). */
public final class KfcTick {
    private KfcTick() { throw new UnsupportedOperationException(); }

    public static void execute(ServerCommandSource source) {
        KfcGen.instantExecuteFunction(source, Identifier.of("map", "tick"));
    }

    /** mcfunction return 값 전파 - if function / store result 호출용. */
    public static int executeReturn(ServerCommandSource source) {
        return KfcGen.instantExecuteFunctionReturn(source, Identifier.of("map", "tick"));
    }

    public static void execute(ServerCommandSource source, java.util.Map<String, String> macroArgs) {
        KfcGen.instantExecuteFunction(source, Identifier.of("map", "tick"), macroArgs);
    }
}
