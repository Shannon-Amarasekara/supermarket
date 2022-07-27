package kata.supermarket;

import kata.supermarket.discountcodes.ItemType;

public enum ProductId {
    SHAMPOO (0, ItemType.UNIT_ITEM),
    CARROT(1, ItemType.WEIGHED_ITEM),
    PICK_AND_MIX(3, ItemType.WEIGHED_ITEM),
    PINT_OF_MILK(4, ItemType.UNIT_ITEM),
    PACK_OF_DIGESTIVES(5, ItemType.UNIT_ITEM),
    AMERICAN_SWEETS(6, ItemType.WEIGHED_ITEM);


    private final int id;
    private final ItemType itemType;

    ProductId(int id, ItemType itemType){
        this.id = id;
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public ItemType getItemType(){
        return itemType;
    }
}
