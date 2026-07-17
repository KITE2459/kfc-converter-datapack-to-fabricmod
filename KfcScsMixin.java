package __KFC_GROUP__.mixin;

import net.minecraft.command.ReturnValueConsumer;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.network.message.SignedCommandArguments;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.thread.FutureQueue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * [D-10 — SCS 체인 압축] ServerCommandSource 접근자/생성자 인보커 믹스인.
 *
 * 목적: execute 재바인딩 연쇄(withEntity → withPosition → withRotation …)는 with* 하나마다
 *      14필드 SCS 전체 복사를 만든다(팬아웃 핫패스에서 엔티티당 체인 길이만큼 할당).
 *      KfcGen.atEntity/withEntityAt 이 이 믹스인의 kfc$create(전체 필드 protected 생성자,
 *      1.21.5 yarn javadoc 으로 시그니처 확정)로 '단일 생성' 리바인드를 수행해 체인 길이 → 1회.
 *
 * 구성: 대부분의 필드는 공개 게터(getWorld/getServer/getName/getDisplayName/getEntity/
 *      isSilent/getReturnValueConsumer/getEntityAnchor/getSignedArguments/
 *      getMessageChainTaskQueue)로 읽는다. 게터가 없는 output·level 두 필드만 @Accessor.
 *
 * fail-safe: 이 믹스인이 적용되지 않으면 SCS 가 이 인터페이스를 구현하지 않으므로 KfcGen 의
 *      `instanceof` 검사가 거짓 → 기존 with* 체인 폴백(관측 동일, 최적화만 소실). 크래시 경로 없음.
 */
@Mixin(ServerCommandSource.class)
public interface KfcScsMixin {

    @Accessor("output")
    CommandOutput kfc$output();

    @Accessor("level")
    int kfc$level();

    /** protected ServerCommandSource(output, pos, rot, world, level, name, displayName, server,
     *  entity, silent, resultStorer, entityAnchor, signedArguments, messageChainTaskQueue) */
    @Invoker("<init>")
    static ServerCommandSource kfc$create(CommandOutput output, Vec3d pos, Vec2f rot,
            ServerWorld world, int level, String name, Text displayName, MinecraftServer server,
            Entity entity, boolean silent, ReturnValueConsumer returnValueConsumer,
            EntityAnchorArgumentType.EntityAnchor entityAnchor,
            SignedCommandArguments signedArguments, FutureQueue messageChainTaskQueue) {
        throw new AssertionError("mixin invoker not applied");
    }
}
