package kata.supermarket.discountcodes;

public enum DiscountCode {
    BUY_ONE_GET_ONE_FREE(0, ItemType.UNIT_ITEM),
    BUY_ONE_KILO_GET_NEXT_KILO_HALF_PRICE(1, ItemType.WEIGHED_ITEM);

    private final int codeValue;
    private final ItemType itemType;

    DiscountCode(int codeValue, ItemType itemType){
        this.codeValue = codeValue;
        this.itemType = itemType;
    }

    public int getCodeValue() {
        return codeValue;
    }

    public ItemType getDiscountItemType(){
        return itemType;
    }
}

