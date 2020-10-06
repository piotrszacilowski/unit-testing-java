import java.math.BigDecimal;
import java.math.MathContext;

public class VatService {
    BigDecimal vatValue;

    public VatService() {
        this.vatValue = new BigDecimal("0.23");
    }

    public BigDecimal getGrossPriceForDefaultVat(Product product) {
        return getGrossPrice(product.getNetPrice(), vatValue);
    }

    public BigDecimal getGrossPrice(BigDecimal netPrice, BigDecimal vatValue) {
        MathContext m = new MathContext(4);
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).round(m);
    }
}
