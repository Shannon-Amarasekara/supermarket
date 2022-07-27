package kata.supermarket;

import kata.supermarket.discountcodes.DiscountCode;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product(ProductId.SHAMPOO, price, Collections.emptySet()).oneOf().price());
    }

    @Test
    void getProductId() {
        final BigDecimal price = new BigDecimal("2.49");
        final Product product = new Product(ProductId.SHAMPOO, price, Collections.emptySet());
        ProductId productId = product.productId();
        assertEquals(productId, ProductId.SHAMPOO);
    }

    @Test
    void getDiscountCodes(){
        final BigDecimal price = new BigDecimal("2.49");
        final Product product = new Product(ProductId.SHAMPOO, price, Set.of(DiscountCode.BUY_ONE_GET_ONE_FREE));
        Set<DiscountCode> discountCodes = product.discountCodes();
        assertEquals(discountCodes, Set.of(DiscountCode.BUY_ONE_GET_ONE_FREE));
    }
}