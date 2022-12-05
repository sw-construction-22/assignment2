package Testing.Card;

import Game.GameTurn;
import Materials.Card.Card;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireworksCardTest {

        private Player player;

        @Before
        public void setup() {
            setupTest();
        }

        @Test
        public void testFireworks() {
            GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(1))));
            Deck bonusCardDeck = new Deck(CardType.FIREWORKS);
            Card c = bonusCardDeck.draw();
            c.executeTurn(gt);
            assertEquals(c, gt.getDrawnCards());
        }

        private void setupTest() {
            player = mock(Player.class);

            when(player.roll(new ArrayList<>(Arrays.asList(new Dice(3), new Dice(3), new Dice(3)))))
                    .thenReturn(new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(1))))
                    .thenReturn(new ArrayList<>(Arrays.asList(new Dice(1), new Dice(2), new Dice(3))))
                    .thenReturn(new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(5)))); //any subsequent call will return -1

        }
    }
