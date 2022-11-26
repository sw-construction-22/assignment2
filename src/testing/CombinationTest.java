package testing;

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
    private final List<Dice> ONES_AND_FIVES_2 =                   new ArrayList<>(Arrays.asList( new Dice(1), new Dice(1), new Dice(3), new Dice(4), new Dice(5), new Dice(5)));
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
        assertTrue(c.evaluateRoll(SIXER));
        assertTrue(c.evaluateRoll(ONES_AND_FIVES));
        assertTrue(c.evaluateRoll(ONES_AND_FIVES_2));
        assertTrue(c.evaluateRoll(NO_TRIPPLETS_BUT_ONES));
        assertTrue(c.evaluateRoll(FULL_TRIPPLE));
        assertTrue(c.evaluateRoll(FULL_TRIPPLE_ONE));
        assertTrue(c.evaluateRoll(REDUCED_SET_TRIPPLES));
        assertTrue(c.evaluateRoll(REDUCED_SET_TRIPPLES_AND_ONES));
        assertTrue(c.evaluateRoll(STRAIGHT));
        assertFalse(c.evaluateRoll(NULL));
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(EMPTY);
        });
        assertThrows(AssertionError.class, () -> {
            c.evaluateRoll(TOO_BIG);
        });
    }
}
