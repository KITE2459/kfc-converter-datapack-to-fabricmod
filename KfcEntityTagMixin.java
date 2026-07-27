package __KFC_GROUP__.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * [B1 — 태그 버킷 전면 폐기 제거] 커맨드 태그 변이를 <b>발생 지점</b>에서 잡아 버킷을 증분 유지한다.
 *
 * <p><b>문제</b> — 태그 버킷은 틱을 넘어 살아남도록 설계됐지만, 외부 명령이 태그를 바꿀 가능성이
 * 있으면 <i>무엇이 바뀌었는지 모르므로</i> fail-closed 로 버킷 전체를 버렸다:
 * <pre>
 *   bridgeReconcile: if ((mask &amp; BR_TAG) != 0) { TAG_BUCKETS.clear(); TB_EPOCH++; }
 * </pre>
 * 실측 로그(16인 주행)가 이것이 남은 재구축의 <b>100%</b> 임을 확정했다:
 * <pre>
 *   [KFC-TAGBUCKET] 100틱: 재구축 100 (틱당 1.00) 사유 gen=0 ...  스캔엔티티누계=250,400
 *   [KFC-TAGBUCKET]   brTag=100  brTag유발: cmd:execute:mask48x100
 * </pre>
 * {@code mask 48 = BR_TAG|BR_NBT} 는 분류기상 {@code execute … run tag …} 뿐이다. 즉
 * <b>커맨드블럭이 매 틱 태그 하나를 바꾸는 것 때문에 엔티티 2,504개를 매 틱 전수 재스캔</b>했다.
 *
 * <p><b>기법</b> — {@code Entity.addCommandTag}/{@code removeCommandTag} 는 커맨드 태그의
 * <b>유일한 정상 변이 경로</b>다(1.21.5 확인: 필드 {@code commandTags} 는 private, 변경 API 는
 * 이 둘뿐이며 {@code /tag} 명령도 이 경로를 탄다). 여기서 실제 변화가 일어났을 때만
 * 해당 버킷을 O(1)로 갱신하면, 누가 태그를 바꾸든(우리 코드·바닐라 명령·타 모드) 버킷이
 * 정확히 유지되어 <b>전면 폐기가 불필요</b>해진다.
 *
 * <p><b>자기 부트스트랩 게이트</b> — 이 훅이 실제로 붙어 동작하는 것이 확인되기 전에는
 * 분류기가 종전대로 {@code BR_TAG} 를 유지한다(안전). 첫 발동 시 {@code KfcGen} 이
 * {@code TAG_HOOK_ACTIVE} 를 켜고 마스크 memo 를 비워, 이후 {@code tag} 명령이
 * {@code BR_NBT} 만 내도록 재계산되게 한다. 믹스인이 아예 적용되지 않으면 플래그가 영원히
 * false 로 남아 <b>종전 동작 그대로</b>다 — fail-safe.
 *
 * <p><b>범위 밖(그대로 유지)</b> — {@code /data modify entity … Tags} 는 {@code readNbt} 로
 * 태그 집합을 통째로 교체하므로 이 훅을 타지 않는다. 분류기는 그 경우 계속 {@code BR_TAG} 를
 * 내고({@code data} 케이스의 {@code tagsTouch} 판정), {@code readNbtTagAware} 의 전후 비교도
 * 그대로 남는다. 청크 로드로 유입된 엔티티는 개체군 지문(popDrift)이 잡는다.
 *
 * <p>{@code require = 0} (fail-safe): 주입 실패 시 크래시 없이 종전 경로 유지.
 */
@Mixin(Entity.class)
public abstract class KfcEntityTagMixin {

    @Inject(method = "addCommandTag(Ljava/lang/String;)Z", at = @At("RETURN"), require = 0)
    private void kfc$onCommandTagAdded(String tag, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            __KFC_GROUP__.generated.KfcGen.onCommandTagAdded((Entity) (Object) this, tag);
        }
    }

    @Inject(method = "removeCommandTag(Ljava/lang/String;)Z", at = @At("RETURN"), require = 0)
    private void kfc$onCommandTagRemoved(String tag, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            __KFC_GROUP__.generated.KfcGen.onCommandTagRemoved((Entity) (Object) this, tag);
        }
    }
}