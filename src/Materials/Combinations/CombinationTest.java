package Materials.Combinations;

import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Dice.Dice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CombinationTest {

    private final List<Dice> SIXER =                            new ArrayList<>(Arrays.asList( new Dice(6),  new Dice(6),  new Dice(6)));
    private final List<Dice> NULL =                             new ArrayList<>(Arrays.asList( new Dice(2), new Dice(2), new Dice(3), new Dice(4), new Dice(6), new Dice(6)));
    private final List<Dice> ONES_AND_FIVES =                   new ArrayList<>(Arrays.asList( new Dice(1), new Dice(5), new Dice(2), new Dice(1), new Dice(2), new Dice(6)));
    private final List<Dice> NO_TRIPPLETS_BUT_ONES =            new ArrayList<>(Arrays.asList( new Dice(1), new Dice(2), new Dice(1), new Dice(4), new Dice(4), new Dice(6)));
    private final List<Dice> FULL_TRIPPLE =                     new ArrayList<>(Arrays.asList( new Dice(2), new Dice(2), new Dice(2), new Dice(2), new Dice(2), new Dice(2)));
    private final List<Dice> FULL_TRIPPLE_ONE =                 new ArrayList<>(Arrays.asList( new Dice(1), new Dice(1), new Dice(1), new Dice(1), new Dice(1), new Dice(1)));
    private final List<Dice> REDUCED_SET_TRIPPLES =             new ArrayList<>(Arrays.asList( new Dice(2), new Dice(2), new Dice(2), new Dice(2)));
    private final List<Dice> REDUCED_SET_TRIPPLES_AND_ONES =    new ArrayList<>(Arrays.asList( new Dice(1), new Dice(1), new Dice(1), new Dice(1)));
    private final List<Dice> STRAIGHT =                         new ArrayList<>(Arrays.asList( new Dice(1),  new Dice(2),  new Dice(3), new Dice(4), new Dice(5), new Dice(6)));
    private final List<Dice> EMPTY =                            new ArrayList<>();
    private final List<Dice> TOO_BIG =                          new ArrayList<>(Arrays.asList( new Dice(1),  new Dice(2),  new Dice(3), new Dice(4), new Dice(5), new Dice(6), new Dice(1)));
    Combination c = new Combination();
    @Test
    public void testBonus200CardCombinations(){
        Deck bonus200CardDeck = new Deck(CardType.BONUSx200);
        Card card = bonus200CardDeck.draw();
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(NO_TRIPPLETS_BUT_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO, DicePattern.TRIPPLE_TWO)), c.evaluateRoll(FULL_TRIPPLE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(FULL_TRIPPLE_ONE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO)), c.evaluateRoll(REDUCED_SET_TRIPPLES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(STRAIGHT, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(NULL, card.getCardType()));
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(EMPTY, card.getCardType());
        });
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(TOO_BIG, card.getCardType());
        });
    }

    @Test
    public void testBonus300CardCombinations(){
        Deck bonus300CardDeck = new Deck(CardType.BONUSx300);
        Card card = bonus300CardDeck.draw();
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(NO_TRIPPLETS_BUT_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO, DicePattern.TRIPPLE_TWO)), c.evaluateRoll(FULL_TRIPPLE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(FULL_TRIPPLE_ONE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO)), c.evaluateRoll(REDUCED_SET_TRIPPLES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(STRAIGHT, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(NULL, card.getCardType()));
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(EMPTY, card.getCardType());
        });
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(TOO_BIG, card.getCardType());
        });
    }
    @Test
    public void testCloverleafCardCombinations(){
        Deck cloverleafCardDeck = new Deck(CardType.CLOVERLEAF);
        Card card = cloverleafCardDeck.draw();
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(NO_TRIPPLETS_BUT_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO, DicePattern.TRIPPLE_TWO)), c.evaluateRoll(FULL_TRIPPLE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(FULL_TRIPPLE_ONE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO)), c.evaluateRoll(REDUCED_SET_TRIPPLES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(STRAIGHT, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(NULL, card.getCardType()));
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(EMPTY, card.getCardType());
        });
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(TOO_BIG, card.getCardType());
        });
    }
    @Test
    public void testStraightCardCombinations(){
        Deck straightCardDeck = new Deck(CardType.STRAIGHT);
        Card card = straightCardDeck.draw();
        assertEquals(new ArrayList<>(), c.evaluateRoll(SIXER, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(ONES_AND_FIVES, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(NO_TRIPPLETS_BUT_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(FULL_TRIPPLE, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(FULL_TRIPPLE_ONE, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(REDUCED_SET_TRIPPLES, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.STRAIGHT)), c.evaluateRoll(STRAIGHT, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(NULL, card.getCardType()));
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(EMPTY, card.getCardType());
        });
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(TOO_BIG, card.getCardType());
        });
    }
    @Test
    public void testDoubleCardCombinations(){
        Deck doubleCardDeck = new Deck(CardType.X2);
        Card card = doubleCardDeck.draw();
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(NO_TRIPPLETS_BUT_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO, DicePattern.TRIPPLE_TWO)), c.evaluateRoll(FULL_TRIPPLE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(FULL_TRIPPLE_ONE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO)), c.evaluateRoll(REDUCED_SET_TRIPPLES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(STRAIGHT, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(NULL, card.getCardType()));
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(EMPTY, card.getCardType());
        });
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(TOO_BIG, card.getCardType());
        });
    }
    @Test
    public void testPlusMinusCardCombinations(){
        Deck plusMinusCardDeck = new Deck(CardType.PLUSMINUS);
        Card card = plusMinusCardDeck.draw();
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(NO_TRIPPLETS_BUT_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO, DicePattern.TRIPPLE_TWO)), c.evaluateRoll(FULL_TRIPPLE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(FULL_TRIPPLE_ONE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO)), c.evaluateRoll(REDUCED_SET_TRIPPLES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(STRAIGHT, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(NULL, card.getCardType()));
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(EMPTY, card.getCardType());
        });
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(TOO_BIG, card.getCardType());
        });
    }
    @Test
    public void testFireworksCardCombinations(){
        Deck fireworksCardDeck = new Deck(CardType.FIREWORKS);
        Card card = fireworksCardDeck.draw();
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(NO_TRIPPLETS_BUT_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO, DicePattern.TRIPPLE_TWO)), c.evaluateRoll(FULL_TRIPPLE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(FULL_TRIPPLE_ONE, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO)), c.evaluateRoll(REDUCED_SET_TRIPPLES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES, card.getCardType()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(STRAIGHT, card.getCardType()));
        assertEquals(new ArrayList<>(), c.evaluateRoll(NULL, card.getCardType()));
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(EMPTY, card.getCardType());
        });
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(TOO_BIG, card.getCardType());
        });
    }

    @Test
    public void testMaxDiceValueFromCombinations(){
        Deck fireworksCardDeck = new Deck(CardType.FIREWORKS);
        Card card = fireworksCardDeck.draw();
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER, card.getCardType()));
        assertEquals(600, c.dicePatternMaxPoints());
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES, card.getCardType()));
        assertEquals(250, c.dicePatternMaxPoints());
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(FULL_TRIPPLE_ONE, card.getCardType()));
        assertEquals(2000, c.dicePatternMaxPoints());
    }
    @Test
    public void testPatternDiceSizeFromCombinations(){
        Deck fireworksCardDeck = new Deck(CardType.FIREWORKS);
        Card card = fireworksCardDeck.draw();
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER, card.getCardType()));
        assertEquals(3, c.dicePatternSize());
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES, card.getCardType()));
        assertEquals(3, c.dicePatternSize());
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(FULL_TRIPPLE_ONE, card.getCardType()));
        assertEquals(6, c.dicePatternSize());
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.getFoundPatterns());
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES, card.getCardType()));
        assertEquals(4, c.dicePatternSize());
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.getFoundPatterns());
    }

}
