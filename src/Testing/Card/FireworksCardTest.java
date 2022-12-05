package Testing.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Card.Card;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * to run the project download there are two approaches:
 * - as this is a java project add the mockito-core jar to the classpath (download it from the maven repo)
 * - create a new maven project and add it as a dependency
 */

/**
 * author: daniel lutziger
 */
public class FireworksCardTest {

        private Player player;

        @BeforeEach
        public void setup() {
            setupTest();
        }

        @Test
        public void testFireworksScoreTutto() {
            GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(1))));
            Deck bonusCardDeck = new Deck(CardType.FIREWORKS);
            Card c = bonusCardDeck.draw();
            gt.addCard(c);
            c.executeTurn(gt);

            assertEquals(c, gt.getDrawnCards().get(0).getCard());
            assertEquals(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice()), gt.getDice());
            assertEquals(GameState.FIRSTROLL, gt.getState());
            assertEquals(0, c.applyCardEffect(player.getScore()));
        }

    @Test
    public void testFireworksScore() {
        GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(4))));
        Deck bonusCardDeck = new Deck(CardType.FIREWORKS);
        Card c = bonusCardDeck.draw();
        gt.addCard(c);
        c.executeTurn(gt);

        assertEquals(c, gt.getDrawnCards().get(0).getCard());
        assertEquals(Arrays.asList(new Dice(4)), gt.getDice());
        assertEquals(GameState.END, gt.getState());
        assertEquals(0, c.applyCardEffect(player.getScore()));
    }
    @Test
    public void testFireworksNullScore() {
        GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(4), new Dice(2), new Dice(4))));
        Deck bonusCardDeck = new Deck(CardType.FIREWORKS);
        Card c = bonusCardDeck.draw();
        gt.addCard(c);
        c.executeTurn(gt);

        assertEquals(c, gt.getDrawnCards().get(0).getCard());
        assertEquals(Arrays.asList(new Dice(4), new Dice(2), new Dice(4)), gt.getDice());
        assertEquals(GameState.END, gt.getState());
        assertEquals(0, c.applyCardEffect(player.getScore()));
    }
    private void setupTest() {
        player = mock(Player.class);
        when(player.roll(anyList()))
                .thenReturn(Arrays.asList(new Dice(1), new Dice(1), new Dice(4)));
    }
}
