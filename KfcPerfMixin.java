package kartriderpack.mixin;

import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAttachments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * [20차 — 바닐라 초월 최적화 / 25차 개정: refmap 무의존] 대형 승객 리스트(수백 파츠 모델 리그)
 * 에서 바닐라의 O(승객수²) 틱 비용을 O(승객수)로 낮춘다. spark 실측: tickPassenger →
 * updatePassengerPosition → hasPassenger(ImmutableList.contains) ~1.2%p +
 * getPassengerAttachmentPos(List.indexOf) ~0.25%p — 승객마다 선형 탐색이라 파츠² 스케일.
 *
 * 방식: passengerList 는 불변 리스트라 '변이 = 항상 새 객체로 교체'다. 리스트 객체 identity 를
 * 스탬프로 (passenger → index) 맵을 지연 재구축하면, 변이 지점에 일절 주입하지 않고도 어떤
 * 변이 경로와도 정합이 보장된다. Entity.equals 는 identity 이므로 Reference 맵과
 * contains/indexOf 시맨틱이 동일하다.
 *
 * [25차 개정 — 왜 refmap 무의존인가] 드롭인 빌드에서 refmap 미생성 시 @Shadow(yarn 필드명)가
 * 런타임 intermediary 에서 해석되지 못해 통째로 비활성이었다(실측 로그: "No refMap loaded").
 *  - 필드 @Shadow 제거: 공개 getPassengerList() '코드 참조'는 remapJar 가 항상 리맵한다.
 *  - 어노테이션 타깃은 intermediary(프로덕션)/yarn(개발) 이중 명시 + remap=false —
 *    환경마다 한쪽이 매치되고(require=0), refmap 조회 자체를 하지 않는다.
 *  - intermediary 이름은 1.21.5 yarn 매핑에서 확정: hasPassenger(Entity)=method_5626,
 *    static getPassengerAttachmentPos=method_55665 (각 이름은 클래스 내 유일 — 서명 생략 안전).
 *
 * fail-closed:
 *  - 승객 8명 미만은 바닐라 경로 그대로(동작·비용 모두 불변, 오버헤드 0).
 *  - mixins.json 이 required=false / defaultRequire=0, 각 주입도 require=0 이라 미래 MC 에서
 *    타깃이 사라져도 크래시 없이 바닐라 경로로 남는다(최적화만 소실).
 */
@Mixin(Entity.class)
public abstract class KfcPerfMixin {
    @Unique private static final int KFC$LINEAR_MAX = 8;
    @Unique private List<Entity> kfc$idxList;
    @Unique private Reference2IntOpenHashMap<Entity> kfc$idx;

    @Unique
    private int kfc$indexOf(List<Entity> l, Entity p) {
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
    @Inject(method = {"method_5626", "hasPassenger(Lnet/minecraft/entity/Entity;)Z"},
            at = @At("HEAD"), cancellable = true, require = 0, remap = false)
    private void kfc$hasPassengerFast(Entity passenger, CallbackInfoReturnable<Boolean> cir) {
        List<Entity> l = ((Entity) (Object) this).getPassengerList();
        if (l.size() >= KFC$LINEAR_MAX) {
            cir.setReturnValue(kfc$indexOf(l, passenger) >= 0);
        }
    }

    /** static getPassengerAttachmentPos 내부의 getPassengerList().indexOf(passenger) 대체.
     *  리다이렉트 수신 리스트는 vehicle 의 승객 리스트와 동일 객체이므로 vehicle 측 캐시를 쓴다. */
    @Redirect(method = {"method_55665",
                        "getPassengerAttachmentPos(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/EntityAttachments;)Lnet/minecraft/util/math/Vec3d;"},
              at = @At(value = "INVOKE",
                       target = "Ljava/util/List;indexOf(Ljava/lang/Object;)I", remap = false),
              require = 0, remap = false)
    private static int kfc$attachmentIndexOf(List<?> list, Object passenger,
                                             Entity vehicle, Entity passenger2, EntityAttachments attachments) {
        if (list.size() >= KFC$LINEAR_MAX && passenger instanceof Entity pe) {
            @SuppressWarnings("unchecked")
            List<Entity> le = (List<Entity>) list;
            return ((KfcPerfMixin) (Object) vehicle).kfc$indexOf(le, pe);
        }
        return list.indexOf(passenger);
    }
}