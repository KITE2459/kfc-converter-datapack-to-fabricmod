package __KFC_GROUP__.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * [27차 — 엔티티 부착 캐시 슬롯] KfcGen 의 엔티티-키 캐시를 <b>해시맵에서 엔티티 필드로</b>
 * 옮긴다. 순수 저장소 믹스인이라 주입(@Inject/@Redirect)이 하나도 없다 — 바닐라 코드 경로를
 * 건드리지 않고 필드 2개와 접근자만 Entity 에 병합한다.
 *
 * <p><b>근거(spark 실측, 16인 주행 / 실작업 27,048ms)</b>
 * <pre>
 *   java.util.IdentityHashMap.get      444ms (1.64%)
 *   java.lang.System.identityHashCode  272ms (1.01%)
 *   KfcGen.ndOf              self      504ms (1.86%)   ← 본문은 맵 조회 한 줄
 * </pre>
 * 이 캐시는 '이미 손에 든 엔티티'를 키로 조회한다. 값을 그 객체에 얹으면 조회가 필드 로드
 * 1회로 사라진다. 슬롯은 withEntitySrc 의 (name, displayName) 하나뿐이다.
 * (27차 초안의 '승객 트리 평탄화' 슬롯은 회귀를 내고 철회됐다 — KfcGen 쪽 이력 주석 참조.)
 *
 * <p><b>정합</b> — 슬롯은 KfcGen 의 전역 스탬프 {@code ND_STAMP} 와 일치할 때만 유효하다.
 * 종전 '맵 전량 폐기'는 스탬프 증가(O(1))로 1:1 대응되며 무효화 시점과 범위는 완전히 동일하다
 * (무효화 축: 서버 교체 / {@code NAME_GEN} / 주기 화해 / {@code invalidateNameOf}).
 * 슬롯은 엔티티와 함께 소멸하므로 종전 맵이 죽은 엔티티를 붙잡던 강참조도 사라진다.
 *
 * <p><b>fail-safe</b> — 이 믹스인이 적용되지 않으면 Entity 가 {@code KfcGen.NdHolder} 를
 * 구현하지 않아 KfcGen 의 {@code instanceof} 가 거짓 → 종전 IdentityHashMap 경로로 폴백한다(관측 동일, 최적화만 소실). 주입이 없으므로
 * 미래 MC 에서 타깃 메서드가 바뀌어도 깨질 지점 자체가 없다.
 *
 * <p><b>스레드</b> — KfcGen 과 동일하게 서버 메인 스레드 전용 계약. 슬롯은 KfcGen 만 읽고 쓴다.
 */
@Mixin(Entity.class)
public abstract class KfcEntityNdMixin implements __KFC_GROUP__.generated.KfcGen.NdHolder {

    @Unique private Object[] kfc$ndSlot;
    @Unique private long kfc$ndStampSlot;

    @Override public Object[] kfc$nd()            { return this.kfc$ndSlot; }
    @Override public void kfc$nd(Object[] v)      { this.kfc$ndSlot = v; }
    @Override public long kfc$ndStamp()           { return this.kfc$ndStampSlot; }
    @Override public void kfc$ndStamp(long s)     { this.kfc$ndStampSlot = s; }
}
