package kata.supermarket;

import kata.supermarket.discountcodes.DiscountCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket(new DiscountingService());
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                oneItemPricedPerUnitAndOneItemPricedPerWeightBothHavingBuyOneGetOneFreeDiscount(),
                twoItemsPricedPerUnitWithBuyOneGetOneFreeDiscount(),
                threeItemsPricedPerUnitWithBuyOneGetOneFreeDiscount()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }


    private static Arguments oneItemPricedPerUnitAndOneItemPricedPerWeightBothHavingBuyOneGetOneFreeDiscount(){
        return Arguments.of("one item priced per unit and one item priced per weight both with buy one get one free discount", "5.48",
                Set.of(aPintOfMilkWithBuyOneGetOneFreeDiscount(), aKiloOfAmericanSweets()));
    }

    private static Arguments twoItemsPricedPerUnitWithBuyOneGetOneFreeDiscount(){
        return Arguments.of("two same items priced per unit with buy one get one free discount, and a different item priced per unit", "2.04",
                Set.of(aPackOfDigestivesWithBuyOneGetOneFreeDiscount(), aPackOfDigestivesWithBuyOneGetOneFreeDiscount(), aPintOfMilk()));
    }

    private static Arguments threeItemsPricedPerUnitWithBuyOneGetOneFreeDiscount(){
        return Arguments.of("three same items priced per unit with buy one get one free discount, and a different priced per unit ", "2.53",
                Set.of(aPintOfMilkWithBuyOneGetOneFreeDiscount(), aPintOfMilkWithBuyOneGetOneFreeDiscount(), aPintOfMilkWithBuyOneGetOneFreeDiscount(), aPackOfDigestives()));
    }


    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(ProductId.PINT_OF_MILK, new BigDecimal("0.49"), Collections.emptySet()).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(ProductId.PACK_OF_DIGESTIVES, new BigDecimal("1.55"), Collections.emptySet()).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(ProductId.AMERICAN_SWEETS, new BigDecimal("4.99"), Collections.emptySet());
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(ProductId.PICK_AND_MIX, new BigDecimal("2.99"), Collections.emptySet());
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    private static Item aPintOfMilkWithBuyOneGetOneFreeDiscount(){
        return new Product(ProductId.PINT_OF_MILK, new BigDecimal("0.49"), Set.of(DiscountCode.BUY_ONE_GET_ONE_FREE)).oneOf();
    }

    private static Item aPackOfDigestivesWithBuyOneGetOneFreeDiscount() {
        return new Product(ProductId.PACK_OF_DIGESTIVES, new BigDecimal("1.55"), Set.of(DiscountCode.BUY_ONE_GET_ONE_FREE)).oneOf();
    }

}