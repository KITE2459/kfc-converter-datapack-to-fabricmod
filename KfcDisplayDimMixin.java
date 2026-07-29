package __KFC_GROUP__.mixin;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * [32차 — 디스플레이 이동 경로의 DataTracker 조회 제거] {@code DisplayEntity.setPosition} 은
 * <b>매 이동마다</b> {@code updateVisibilityBoundingBox()} 를 호출하고, 그 본문은
 * {@code getDisplayWidth()/getDisplayHeight()} = <b>DataTracker.get 2회</b>를 수행한다.
 * 카트 리그(수백 파츠) × 틱당 다중 미세이동에서 이 조회가 spark 의
 * {@code DataTracker.get(method_12781) 0.16 ms/tick} 지분의 핫 원천이다.
 *
 * <p><b>기법</b> — width/height 를 {@code @Unique} 필드에 캐시하고 본문을 HEAD-cancel 로
 * 동일 산식 재현(바닐라 본문과 문장 단위 일치 — tooSmallToRender/visibilityBoundingBox 갱신 포함).
 * 결과 상태는 바닐라와 완전 동일, 트래커 조회만 필드 로드로 대체된다.
 *
 * <p><b>무효화(완전성 근거)</b> — WIDTH/HEIGHT 의 유일한 변이 표면은 {@code dataTracker.set}
 * 이고(세터·readNbt 의 세터 경유·타 모드 직접 set 전부), 모든 set 은
 * {@code onTrackedDataSet} 콜백을 발화한다(동일값 set 은 바닐라가 콜백 자체를 생략 — 변화 없음).
 * 그 HEAD 에서 캐시를 무효화하므로 본문의 재계산이 항상 신선한 값을 읽는다. 축이 '변이 지점
 * 그 자체'라 누락이 구조적으로 불가능하다(30차 KfcRideMixin 과 동일 원칙).
 *
 * <p>다중 변환 모드: 본 믹스인이 N개 적용돼도 각자 HEAD 에서 동일 값을 계산해 같은 필드에
 * 쓰므로 결과 동일(중복 계산만 미미). {@code require=0}: 미래 MC 타깃 소실 시 바닐라 본문 유지.
 */
@Mixin(DisplayEntity.class)
public abstract class KfcDisplayDimMixin {

    @Shadow @Final private static TrackedData<Float> WIDTH;
    @Shadow @Final private static TrackedData<Float> HEIGHT;
    @Shadow private Box visibilityBoundingBox;
    @Shadow private boolean tooSmallToRender;

    @Unique private float kfc$dimW;
    @Unique private float kfc$dimH;
    @Unique private boolean kfc$dimValid;

    /** 변이 신호 — 본문(오버라이드 포함)이 재계산하기 '전에' 캐시를 내린다(HEAD). */
    @Inject(method = "onTrackedDataSet", at = @At("HEAD"), require = 0)
    private void kfc$invalidateDims(TrackedData<?> data, CallbackInfo ci) {
        if (WIDTH.equals(data) || HEIGHT.equals(data)) {
            this.kfc$dimValid = false;
        }
    }

    /** 바닐라 본문과 문장 단위 동일 — width/height 만 캐시 필드에서 공급. */
    @Inject(method = "updateVisibilityBoundingBox", at = @At("HEAD"), cancellable = true, require = 0)
    private void kfc$fastVisibilityBox(CallbackInfo ci) {
        DisplayEntity self = (DisplayEntity) (Object) this;
        if (!this.kfc$dimValid) {
            this.kfc$dimW = self.getDisplayWidth();
            this.kfc$dimH = self.getDisplayHeight();
            this.kfc$dimValid = true;
        }
        float f = this.kfc$dimW;
        float g = this.kfc$dimH;
        this.tooSmallToRender = f == 0.0F || g == 0.0F;
        float h = f / 2.0F;
        double d = self.getX();
        double e = self.getY();
        double i = self.getZ();
        this.visibilityBoundingBox = new Box(d - h, e, i - h, d + h, e + g, i + h);
        ci.cancel();
    }
}
