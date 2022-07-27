package kata.supermarket;

import kata.supermarket.discountcodes.DiscountCode;

import java.math.BigDecimal;
import java.util.Set;

public class Product {

    private final BigDecimal pricePerUnit;
    private final ProductId productId;
    private final Set<DiscountCode> discountCodes;

    public Product(final ProductId productId, final BigDecimal pricePerUnit, Set<DiscountCode> discountCodes) {
        this.pricePerUnit = pricePerUnit;
        this.productId = productId;
        this.discountCodes = discountCodes;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    ProductId productId() {
        return productId;
    }

    Set<DiscountCode> discountCodes() {
        return discountCodes;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
