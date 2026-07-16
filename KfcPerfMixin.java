package __KFC_GROUP__.mixin;

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
 * [20차 — 바닐라 초월 최적화 / 26차 개정: loom in-place 리맵 사용] 대형 승객 리스트(수백 파츠
 * 모델 리그)에서 바닐라의 O(승객수²) 틱 비용을 O(승객수)로 낮춘다. spark 실측: tickPassenger →
 * updatePassengerPosition → hasPassenger(ImmutableList.contains) ~1.2%p +
 * getPassengerAttachmentPos(List.indexOf) ~0.25%p — 승객마다 선형 탐색이라 파츠² 스케일.
 *
 * 방식: passengerList 는 불변 리스트라 '변이 = 항상 새 객체로 교체'다. 리스트 객체 identity 를
 * 스탬프로 (passenger → index) 맵을 지연 재구축하면, 변이 지점에 일절 주입하지 않고도 어떤
 * 변이 경로와도 정합이 보장된다. Entity.equals 는 identity 이므로 Reference 맵과
 * contains/indexOf 시맨틱이 동일하다.
 *
 * [26차 개정 — 왜 intermediary 하드코딩을 제거했나] 25차는 refmap 미생성 환경을 가정해 어노테이션
 * 타깃에 intermediary(method_5626/method_55665) 를 직접 박고 remap=false 로 두었다. 그러나 빌드
 * 설정을 loom.mixin.useLegacyMixinAp=false 로 바꿔 loom 자체 in-place 믹스인 리매퍼가 정상 동작
 * 하면서, 그 하드코딩 intermediary 가 오히려 리매퍼와 충돌해 "Cannot remap method_5626 …" 경고를
 * 내고 주입이 안 붙었다. 이제 KfcFuncCoherenceMixin 과 동일하게 'yarn 시그니처 + remap=true(기본)'
 * 로 두어 loom 이 remapJar 에서 intermediary 로 정확히 리맵하게 한다.
 *   · method 타깃은 오버로드가 있으므로 반드시 '전체 디스크립터' 로 지정(1.21.5 yarn 확인):
 *       hasPassenger(Entity)               — hasPassenger(Predicate) 오버로드와 구분
 *       getPassengerAttachmentPos(Entity,Entity,EntityAttachments)Vec3d — (Entity,EntityDimensions,float) 와 구분
 *   · @At INVOKE 의 java.util.List.indexOf 는 MC 심볼이 아니므로 그 @At 만 remap=false 유지.
 *   · getPassengerList() 는 '코드 참조' 라 remapJar 가 항상 리맵(어노테이션 아님).
 *
 * fail-closed:
 *  - 승객 8명 미만은 바닐라 경로 그대로(동작·비용 모두 불변, 오버헤드 0).
 *  - mixins.json required=false / defaultRequire=0, 각 주입도 require=0 이라 미래 MC 에서 타깃이
 *    사라져도 크래시 없이 바닐라 경로로 남는다(최적화만 소실).
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

    /** hasPassenger(Entity) = passengerList.contains — 대형 리스트만 O(1) 맵 조회로 대체.
     *  yarn 시그니처 + remap=true(기본): loom 이 intermediary 로 리맵. 오버로드 구분 위해 디스크립터 명시. */
    @Inject(method = "hasPassenger(Lnet/minecraft/entity/Entity;)Z",
            at = @At("HEAD"), cancellable = true, require = 0)
    private void kfc$hasPassengerFast(Entity passenger, CallbackInfoReturnable<Boolean> cir) {
        List<Entity> l = ((Entity) (Object) this).getPassengerList();
        if (l.size() >= KFC$LINEAR_MAX) {
            cir.setReturnValue(kfc$indexOf(l, passenger) >= 0);
        }
    }

    /** static getPassengerAttachmentPos 내부의 getPassengerList().indexOf(passenger) 대체.
     *  리다이렉트 수신 리스트는 vehicle 의 승객 리스트와 동일 객체이므로 vehicle 측 캐시를 쓴다.
     *  method 는 remap=true(기본)로 loom 이 리맵; @At target(java List.indexOf)만 remap=false. */
    @Redirect(method = "getPassengerAttachmentPos(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/EntityAttachments;)Lnet/minecraft/util/math/Vec3d;",
              at = @At(value = "INVOKE",
                       target = "Ljava/util/List;indexOf(Ljava/lang/Object;)I", remap = false),
              require = 0)
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