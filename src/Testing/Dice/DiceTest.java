package Testing.Dice;

import Materials.Dice.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiceTest {

    @Test
    public void testDiceCreation(){
        assertThrows(AssertionError.class, () -> new Dice(10));
        assertThrows(AssertionError.class, () -> new Dice(0));
        assertThrows(AssertionError.class, () -> new Dice(7));
        assertEquals(6, new Dice(6).getValue());
    }
}
