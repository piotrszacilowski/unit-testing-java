import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VatServiceTest {

    private VatService vatService;

    @BeforeEach
    void setUp() {
        vatService = new VatService();
    }

    @Test
    void shouldCalculateGrossPriceForDefaultVat() {
        //when
        Product product = new Product(UUID.randomUUID(), new BigDecimal("20.00"));
        BigDecimal result = vatService.getGrossPriceForDefaultVat(product);
        //when
        assertEquals(new BigDecimal("24.60"), result);
    }

    @Test
    void shouldCalculateGrossPriceForCustomVatValue() {
        Product product = new Product(UUID.randomUUID(), new BigDecimal("10.00"));
        BigDecimal result = vatService.getGrossPrice(product.getNetPrice(), new BigDecimal("0.00"));
        //when
        assertEquals(new BigDecimal("10.00"), result);
    }
}
