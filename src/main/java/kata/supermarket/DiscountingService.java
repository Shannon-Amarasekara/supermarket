package kata.supermarket;

import kata.supermarket.discountcodes.DiscountCode;

import java.math.BigDecimal;
import java.util.*;

public class DiscountingService {

    public BigDecimal getTotalDiscount(List<Item> items) {
        Map<ProductId, List<Item>> productWithListOfItems =
                getProductWithListOfItems(items);
        Map<ProductId, Set<DiscountCode>> productWithDiscountCodes =
                getProductWithDiscountCodes(items);

        BigDecimal total = new BigDecimal(0);
        for (ProductId productId : productWithListOfItems.keySet()) {
            List<Item> itemsForProduct = productWithListOfItems.get(productId);
            Set<DiscountCode> discountCodesForProduct = productWithDiscountCodes.get(productId);

            for (DiscountCode discountCode : discountCodesForProduct) {

                switch (discountCode) {
                    case BUY_ONE_GET_ONE_FREE:
                        total = total.subtract(buyOneGetOneFree(itemsForProduct));
                        break;

                    //more cases for discount codes
                }
            }
        }
        return total;
    }

    private Map<ProductId, List<Item>> getProductWithListOfItems(List<Item> items) {
        Map<ProductId, List<Item>> map = new HashMap<>();
        for (Item item : items) {
            ProductId productId = item.productId();
            if (!map.containsKey(productId)) {
                map.put(productId, List.of(item));
            } else {
                List<Item> oldItems = map.get(productId);
                List<Item> newItems = new ArrayList<>(oldItems);
                newItems.add(item);
                map.replace(productId, oldItems, newItems);
            }
        }
        return map;
    }

    private Map<ProductId, Set<DiscountCode>> getProductWithDiscountCodes(List<Item> items) {
        Map<ProductId, Set<DiscountCode>> map = new HashMap<>();
        for (Item item : items) {
            ProductId productId = item.productId();
            if (!map.containsKey(productId)) {
                map.put(productId, item.discountCodes());
            } else {
                Set<DiscountCode> oldDiscountCodes = map.get(productId);
                Set<DiscountCode> newDiscountCodes = new HashSet<>(oldDiscountCodes);
                newDiscountCodes.addAll(item.discountCodes());
                map.replace(productId, oldDiscountCodes, newDiscountCodes);
            }
        }
        return map;
    }

    private BigDecimal buyOneGetOneFree(List<Item> items) {
        BigDecimal amountOfFreeItems = BigDecimal.valueOf(items.size() / 2);
        BigDecimal price = items.get(0).price();
        return price.multiply(amountOfFreeItems);
    }

}
