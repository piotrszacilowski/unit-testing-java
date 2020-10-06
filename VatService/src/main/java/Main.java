import java.math.BigDecimal;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        VatService vatService = new VatService();
        Product product = new Product(UUID.randomUUID(), new BigDecimal("20.00"));
        BigDecimal result = vatService.getGrossPriceForDefaultVat(product);
        System.out.println(result);
    }
}
