package kata.supermarket.discountcodes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCodeTest {

    @Test
    void getCodeValueFromDiscountCode(){
        final DiscountCode discountCode = DiscountCode.BUY_ONE_GET_ONE_FREE;
        int codeValue = discountCode.getCodeValue();
        assertEquals(codeValue, 0);
    }

    @Test
    void getDiscountItemTypeFromDiscountCode(){
        final DiscountCode discountCode = DiscountCode.BUY_ONE_GET_ONE_FREE;
        ItemType itemType = discountCode.getDiscountItemType();
        assertEquals(itemType, ItemType.UNIT_ITEM);
    }

}
