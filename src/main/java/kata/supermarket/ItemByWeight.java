package kata.supermarket;

import kata.supermarket.discountcodes.DiscountCode;
import kata.supermarket.discountcodes.ItemType;

import java.math.BigDecimal;
import java.util.Set;

import static java.math.RoundingMode.HALF_UP;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, HALF_UP);
    }

    @Override
    public ItemType itemType() {
        return product.productId().getItemType();
    }

    @Override
    public Set<DiscountCode> discountCodes() {
        return product.discountCodes();
    }

    @Override
    public ProductId productId() {
        return product.productId();
    }
}
