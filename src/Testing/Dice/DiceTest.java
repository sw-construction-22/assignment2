package Testing.Dice;

import Materials.Dice.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * author: daniel lutiger
 */
public class DiceTest {

    @Test
    public void testDiceCreation(){
        assertThrows(AssertionError.class, () -> new Dice(10));
        assertThrows(AssertionError.class, () -> new Dice(0));
        assertThrows(AssertionError.class, () -> new Dice(7));
        assertEquals(6, new Dice(6).getValue());
        assertEquals(5, new Dice(5).getValue());
        assertEquals(4, new Dice(4).getValue());
        assertEquals(3, new Dice(3).getValue());
        assertEquals(2, new Dice(2).getValue());
        assertEquals(1, new Dice(1).getValue());
    }
}
