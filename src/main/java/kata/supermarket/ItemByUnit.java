package kata.supermarket;

import kata.supermarket.discountcodes.DiscountCode;
import kata.supermarket.discountcodes.ItemType;

import java.math.BigDecimal;
import java.util.Set;

public class ItemByUnit implements Item {

    private final Product product;

    ItemByUnit(final Product product) {
        this.product = product;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    @Override
    public ItemType itemType() {
        return product.productId().getItemType();
    }

    @Override
    public Set<DiscountCode> discountCodes() {
        return product.discountCodes();
    }
}
