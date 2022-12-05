package Testing.Player;

import Game.GameOperator;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {
    Player p = new Player("Testing");
    Deck d = new Deck(CardType.STOP);
    @Test
    public void testDraw(){
        assertEquals(CardType.STOP,p.draw(d).getCardType());
    }
    @Test
    public void testAddScore(){
        p.addScore(1000);
        assertEquals(1000, p.getScore());
    }

    @Test
    public void testSetScore(){
        p.setScore(1000);
        assertEquals(1000, p.getScore());
    }

    @Test
    public void temporaryTest(){
        assertEquals(0, p.getTemporary());
        p.addTemporary(400);
        assertEquals(400, p.getTemporary());
        p.resetTempScore();
        assertEquals(0, p.getTemporary());
    }

    @Test
    public void testGetHoldBack(){
        List<DicePattern> patterns = new ArrayList<>(Arrays.asList(DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE));
        List<Dice > dice= new ArrayList<>(Arrays.asList( new Dice(1), new Dice(5), new Dice(2), new Dice(1), new Dice(2), new Dice(6)));;
        Player mocked = mock(Player.class);
        when(mocked.getInput()).thenReturn("1,2,3");
        when(mocked.holdBack(patterns, dice)).thenCallRealMethod();
        assertEquals(Arrays.asList( new Dice(1), new Dice(1), new Dice(5)), mocked.holdBack(patterns, dice));

        when(mocked.getInput()).thenReturn("1,3");
        assertEquals(Arrays.asList( new Dice(1), new Dice(5)), mocked.holdBack(patterns, dice));

        when(mocked.getInput()).thenReturn("3");
        assertEquals(Arrays.asList(new Dice(5)), mocked.holdBack(patterns, dice));
    }

    @Test
    public void testReroll(){
        Player mocked = mock(Player.class);
        when(mocked.getInput()).thenReturn("r");
        when(mocked.reroll()).thenCallRealMethod();
        assertTrue(mocked.reroll());

        when(mocked.getInput()).thenReturn("R");
        assertTrue(mocked.reroll());
        when(mocked.getInput()).thenReturn("e");
        assertFalse(mocked.reroll());
        when(mocked.getInput()).thenReturn("E");
        assertFalse(mocked.reroll());
    }
}
