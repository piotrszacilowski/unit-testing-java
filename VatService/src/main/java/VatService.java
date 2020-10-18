import java.math.BigDecimal;
import java.math.MathContext;

public class VatService {
    VatProvider vatProvider;

    public VatService(VatProvider vatProvider) {
        this.vatProvider = vatProvider;
    }

    public BigDecimal getGrossPriceForDefaultVat(Product product) throws IncorectVatException {
        return calculateGrossPrice(product.getNetPrice(), vatProvider.getDefaultVat());
    }

    public BigDecimal getGrossPrice(BigDecimal netPrice, String productType) throws IncorectVatException {
        BigDecimal vatValue = vatProvider.getVatForType(productType);
        return calculateGrossPrice(netPrice, vatValue);
    }

    public BigDecimal calculateGrossPrice(BigDecimal netPrice, BigDecimal vatValue) throws IncorectVatException {
        MathContext m = new MathContext(4);
        if (vatValue.compareTo(BigDecimal.ONE) == 1) {
            throw new IncorectVatException("VAT must be lower!");
        }
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).round(m);
    }
}
