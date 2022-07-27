package kata.supermarket;

import kata.supermarket.discountcodes.DiscountCode;

import java.math.BigDecimal;
import java.util.Set;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final ProductId productId;
    private final Set<DiscountCode> discountCodes;

    public WeighedProduct(final ProductId productId, final BigDecimal pricePerKilo, Set<DiscountCode> discountCodes) {
        this.pricePerKilo = pricePerKilo;
        this.productId = productId;
        this.discountCodes = discountCodes;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    ProductId productId() {
        return productId;
    }

    Set<DiscountCode> discountCodes() {
        return discountCodes;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
