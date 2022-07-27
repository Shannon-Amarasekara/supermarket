package kata.supermarket;

import kata.supermarket.discountcodes.DiscountCode;
import kata.supermarket.discountcodes.ItemType;

import java.math.BigDecimal;
import java.util.Set;

public interface Item {
    BigDecimal price();

    ItemType itemType();

    Set<DiscountCode> discountCodes();
}
