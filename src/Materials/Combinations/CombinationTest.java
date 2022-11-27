package Materials.Combinations;

import Game.GameState;
import Game.NormalState;
import Game.NullState;
import Game.TuttoState;
import Materials.Combinations.Combination;
import Materials.Dice.Dice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CombinationTest {

    //evaluate roll
    // input => List<Dice>
    // game state => True / False

    //combination points
    // => List<Dice>
    // => return points
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

    @Test
    public void testEvaluateRoll(){
        Combination c = new Combination();
        assertEquals(c.evaluateRoll(SIXER), new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_SIX)));
        assertEquals(c.evaluateRoll(ONES_AND_FIVES), new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE)));
        assertEquals(c.evaluateRoll(NO_TRIPPLETS_BUT_ONES), new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE)));
        assertEquals(c.evaluateRoll(FULL_TRIPPLE),  new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO, DicePattern.TRIPPLE_TWO)));
        assertEquals(c.evaluateRoll(FULL_TRIPPLE_ONE),  new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.TRIPPLE_ONE)));
        assertEquals(c.evaluateRoll(REDUCED_SET_TRIPPLES),  new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_TWO)));
        assertEquals(c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES),  new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE)));
        assertEquals(c.evaluateRoll(STRAIGHT),  new ArrayList<>(Arrays.asList(DicePattern.STRAIGHT)));
        assertEquals(c.evaluateRoll(NULL), new ArrayList<>());
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(EMPTY);
        });
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(TOO_BIG);
        });
    }

    @Test
    public void testEvaluateState(){
        Combination c = new Combination();
        c.evaluateRoll(FULL_TRIPPLE);
        assertEquals(c.evaluateState(FULL_TRIPPLE.size()), TuttoState.state());
        c.evaluateRoll(SIXER);
        assertEquals(c.evaluateState(SIXER.size()), TuttoState.state());
        c.evaluateRoll(NULL);
        assertEquals(c.evaluateState(NULL.size()), NullState.state());
        c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES);
        assertEquals(c.evaluateState(REDUCED_SET_TRIPPLES_AND_ONES.size()), TuttoState.state());
        c.evaluateRoll(NO_TRIPPLETS_BUT_ONES);
        assertEquals(c.evaluateState(NO_TRIPPLETS_BUT_ONES.size()), NormalState.state());
    }


}
