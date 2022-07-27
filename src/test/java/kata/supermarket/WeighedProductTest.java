package kata.supermarket;

import kata.supermarket.discountcodes.DiscountCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos, String expectedPrice) {
        final WeighedProduct weighedProduct = new WeighedProduct(ProductId.CARROT, new BigDecimal(pricePerKilo), Collections.emptySet());
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "100.00"),
                Arguments.of("100.00", "0.33333", "33.33"),
                Arguments.of("100.00", "0.33335", "33.34"),
                Arguments.of("100.00", "0", "0.00")
        );
    }

    @Test
    void getProductId() {
        final BigDecimal price = new BigDecimal("2.49");
        final WeighedProduct product = new WeighedProduct(ProductId.CARROT, price, Collections.emptySet());
        ProductId productId = product.productId();
        assertEquals(productId, ProductId.CARROT);
    }

    @Test
    void getDiscountCodes(){
        final BigDecimal price = new BigDecimal("2.49");
        final WeighedProduct product = new WeighedProduct(ProductId.CARROT, price, Set.of(DiscountCode.BUY_ONE_KILO_GET_NEXT_KILO_HALF_PRICE));
        Set<DiscountCode> discountCodes = product.discountCodes();
        assertEquals(discountCodes, Set.of(DiscountCode.BUY_ONE_KILO_GET_NEXT_KILO_HALF_PRICE));
    }
}