import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.InstanceOfAssertFactories.type;
import static org.mockito.Mockito.*;

class VatServiceTest {

    VatService vatService;
    VatProvider vatProvider;

    @Test
    @DisplayName("should calculate gross price for default VAT")
    void shouldCalculateGrossPriceForDefaultValue() throws IncorectVatException {
        //given
        when(vatProvider.getDefaultVat()).thenReturn(new BigDecimal("0.23"));
        Product product = generateProductWithPrice("20.00", "Clothes");

        //when
        BigDecimal result = vatService.getGrossPriceForDefaultVat(product);

        //then
        assertThat(result).isEqualTo(new BigDecimal("24.60")); //AssertJ
    }

    @Test
    void shouldThrowExceptionWhenVatIsTooHigh() {
        //given
        String type = "Clothes";
        Product product = generateProductWithPrice("10.00", type);
        when(vatProvider.getVatForType(type)).thenReturn(BigDecimal.TEN);

        //then
        //AssertJ
        assertThatExceptionOfType(IncorectVatException.class).isThrownBy(() -> {
            vatService.getGrossPrice(product.getNetPrice(), type);
        }).withMessage("VAT must be lower!");
    }

    @Test
    void shouldCalculateGrossPriceForCustomVat() throws IncorectVatException {
        //given
        String type = "Toys";
        Product product = generateProductWithPrice("10.00", type);
        when(vatProvider.getVatForType(type)).thenReturn(new BigDecimal("0.00"));

        //when
        BigDecimal result = vatService.getGrossPrice(product.getNetPrice(), type);

        //then
        assertThat(result).isEqualTo(new BigDecimal("10.00"));
    }

    private Product generateProductWithPrice(String vat, String type) {
        return new Product(UUID.randomUUID(), new BigDecimal(vat), type);
    }

    @BeforeEach
    void setUp() {
        vatProvider = mock(VatProvider.class);
        vatService = new VatService(vatProvider);
    }
}
