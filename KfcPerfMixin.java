package kartriderpack.mixin;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAttachments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * [20차 — 바닐라 초월 최적화] 대형 승객 리스트(수백 파츠 모델 리그)에서 바닐라의
 * O(승객수²) 틱 비용을 O(승객수)로 낮춘다. spark 실측(WSlVM8YRbf): tickPassenger →
 * updatePassengerPosition → hasPassenger(ImmutableList.contains) 1.42%p +
 * getPassengerAttachmentPos(List.indexOf) 0.27%p — 승객마다 선형 탐색이라 파츠² 스케일.
 *
 * 방식: passengerList 는 ImmutableList 라 '변이 = 항상 새 객체로 교체'다. 따라서 리스트
 * 객체 identity 를 스탬프로 (passenger → index) 맵을 지연 재구축하면, addPassenger/
 * removePassenger 등 변이 지점에 일절 주입하지 않고도 어떤 변이 경로와도 정합이 보장된다.
 * Entity.equals 는 identity 이므로 Reference 맵과 contains/indexOf 시맨틱이 동일하다.
 *
 * fail-closed:
 *  - 승객 8명 미만은 바닐라 경로 그대로(동작·비용 모두 불변, 오버헤드 0).
 *  - mixins.json 이 required=false / defaultRequire=0 이라 미래 MC 에서 주입이 실패해도
 *    크래시 없이 바닐라 경로로 남는다(최적화만 소실).
 */
@Mixin(Entity.class)
public abstract class KfcPerfMixin {
    @Shadow private ImmutableList<Entity> passengerList;

    @Unique private static final int KFC$LINEAR_MAX = 8;
    @Unique private ImmutableList<Entity> kfc$idxList;
    @Unique private Reference2IntOpenHashMap<Entity> kfc$idx;

    @Unique
    private int kfc$indexOf(Entity p) {
        ImmutableList<Entity> l = this.passengerList;
        int n = l.size();
        if (n < KFC$LINEAR_MAX) return l.indexOf(p);
        if (kfc$idxList != l) {                       // 불변 리스트 — identity 불일치 = 변이 발생
            Reference2IntOpenHashMap<Entity> m = new Reference2IntOpenHashMap<>(n * 2);
            m.defaultReturnValue(-1);
            for (int i = 0; i < n; i++) m.put(l.get(i), i);
            kfc$idx = m;
            kfc$idxList = l;
        }
        return kfc$idx.getInt(p);
    }

    /** hasPassenger(Entity) = passengerList.contains — 대형 리스트만 O(1) 맵 조회로 대체. */
    @Inject(method = "hasPassenger(Lnet/minecraft/entity/Entity;)Z",
            at = @At("HEAD"), cancellable = true)
    private void kfc$hasPassengerFast(Entity passenger, CallbackInfoReturnable<Boolean> cir) {
        if (this.passengerList.size() >= KFC$LINEAR_MAX) {
            cir.setReturnValue(kfc$indexOf(passenger) >= 0);
        }
    }

    /** static getPassengerAttachmentPos 내부의 getPassengerList().indexOf(passenger) 대체.
     *  리다이렉트 수신 리스트는 vehicle.passengerList 와 동일 객체이므로 vehicle 측 캐시를 쓴다. */
    @Redirect(method = "getPassengerAttachmentPos(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/EntityAttachments;)Lnet/minecraft/util/math/Vec3d;",
              at = @At(value = "INVOKE",
                       target = "Ljava/util/List;indexOf(Ljava/lang/Object;)I"))
    private static int kfc$attachmentIndexOf(java.util.List<?> list, Object passenger,
                                             Entity vehicle, Entity passenger2, EntityAttachments attachments) {
        if (list.size() >= KFC$LINEAR_MAX && passenger instanceof Entity pe) {
            return ((KfcPerfMixin) (Object) vehicle).kfc$indexOf(pe);
        }
        return list.indexOf(passenger);
    }
}