package Testing.DicePattern;

import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Card.StraightCard;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * author: daniel lutziger
 */
public class DicePatternTest {

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
    Player p = new Player("TestPlayer");
    static Card c;
    @BeforeAll
    public static void initialize(){
        Deck bonus200CardDeck = new Deck(CardType.BONUSx200);
        c = bonus200CardDeck.draw();
    }
    @Test
    public void testDicePattern(){
        assertEquals(new ArrayList<>(List.of(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER));
    }
    @Test
    public void testBonus200CardCombinations(){
        Deck bonus200CardDeck = new Deck(CardType.BONUSx200);
        Card c = bonus200CardDeck.draw();
        assertEquals(new ArrayList<>(List.of(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES));
        regularCardPatternTest(bonus200CardDeck);
    }

    @Test
    public void testBonus300CardCombinations(){
        Deck bonus300CardDeck = new Deck(CardType.BONUSx300);
        regularCardPatternTest(bonus300CardDeck);
    }
    @Test
    public void testCloverleafCardCombinations(){
        Deck cloverleafCardDeck = new Deck(CardType.CLOVERLEAF);
        regularCardPatternTest(cloverleafCardDeck);
    }
    @Test
    public void testStraightCardCombinations(){
        Deck straightCardDeck = new Deck(CardType.STRAIGHT);
        StraightCard card = (StraightCard) straightCardDeck.draw();
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_SIX)), card.evaluateRoll(SIXER, new ArrayList<DicePattern>()));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE, DicePattern.SINGLE_TWO, DicePattern.SINGLE_SIX)), card.evaluateRoll(ONES_AND_FIVES, new ArrayList<DicePattern>()));
        assertEquals(new ArrayList<>(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE,DicePattern.SINGLE_TWO,DicePattern.SINGLE_FOUR,DicePattern.SINGLE_SIX))), card.evaluateRoll(NO_TRIPPLETS_BUT_ONES, new ArrayList<DicePattern>()));
        assertEquals(new ArrayList<>(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE))), card.evaluateRoll(FULL_TRIPPLE_ONE, new ArrayList<DicePattern>()));
        assertEquals(new ArrayList<>(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_TWO))), card.evaluateRoll(REDUCED_SET_TRIPPLES, new ArrayList<DicePattern>()));
        assertEquals(new ArrayList<>(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE))), card.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES, new ArrayList<DicePattern>()));
        assertEquals(new ArrayList<>(List.of(DicePattern.STRAIGHT)), card.evaluateRoll(STRAIGHT, new ArrayList<DicePattern>()));
        assertEquals(new ArrayList<>(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_TWO, DicePattern.SINGLE_THREE, DicePattern.SINGLE_FOUR, DicePattern.SINGLE_SIX))), card.evaluateRoll(NULL, new ArrayList<DicePattern>()));
        assertThrows(AssertionError.class, () -> card.evaluateRoll(EMPTY, new ArrayList<DicePattern>()));
        assertThrows(AssertionError.class, () -> card.evaluateRoll(TOO_BIG, new ArrayList<DicePattern>()));
    }
    @Test
    public void testDoubleCardCombinations(){
        Deck doubleCardDeck = new Deck(CardType.X2);
        regularCardPatternTest(doubleCardDeck);
    }

    private void regularCardPatternTest(Deck doubleCardDeck) {
        Card card = doubleCardDeck.draw();
        assertEquals(new ArrayList<>(List.of(DicePattern.TRIPPLE_SIX)), c.evaluateRoll(SIXER));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(ONES_AND_FIVES));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(NO_TRIPPLETS_BUT_ONES));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO, DicePattern.TRIPPLE_TWO)), c.evaluateRoll(FULL_TRIPPLE));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(FULL_TRIPPLE_ONE));
        assertEquals(new ArrayList<>(List.of(DicePattern.TRIPPLE_TWO)), c.evaluateRoll(REDUCED_SET_TRIPPLES));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)), c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES));
        assertEquals(new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)), c.evaluateRoll(STRAIGHT));
        assertEquals(new ArrayList<>(), c.evaluateRoll(NULL));
        assertThrows(AssertionError.class, () -> c.evaluateRoll(EMPTY));
        assertThrows(AssertionError.class, () -> c.evaluateRoll(TOO_BIG));
    }

    @Test
    public void testPlusMinusCardCombinations(){
        Deck plusMinusCardDeck = new Deck(CardType.PLUSMINUS);
        regularCardPatternTest(plusMinusCardDeck);
    }
    @Test
    public void testFireworksCardCombinations(){
        Deck fireworksCardDeck = new Deck(CardType.FIREWORKS);
        regularCardPatternTest(fireworksCardDeck);
    }

    @Test
    public void testMaxDiceValueFromCombinations(){
        assertEquals(600, DicePattern.dicePatternMaxPointsFireworks(c.evaluateRoll(SIXER)));
        assertEquals(2600, DicePattern.dicePatternMaxPointsFireworks(c.evaluateRoll(FULL_TRIPPLE_ONE)));
    }

    @Test
    public void testMaxDiceValueFromCombinationsRegularCards(){
        assertEquals(600, DicePattern.dicePatternMaxPoints(c.evaluateRoll(SIXER)));
        assertEquals(2000, DicePattern.dicePatternMaxPoints(c.evaluateRoll(FULL_TRIPPLE_ONE)));
    }
    @Test
    public void testMaxDiceSizeFromCombinations(){
        assertEquals(3, DicePattern.dicePatternSizeFireworks(c.evaluateRoll(SIXER)));
        assertEquals(12, DicePattern.dicePatternSizeFireworks(c.evaluateRoll(FULL_TRIPPLE_ONE)));
    }

    @Test
    public void testMaxDiceSizeFromCombinationsRegularCards(){
        assertEquals(3, DicePattern.dicePatternSize(c.evaluateRoll(SIXER)));
        assertEquals(6, DicePattern.dicePatternSize(c.evaluateRoll(FULL_TRIPPLE_ONE)));
    }
}
