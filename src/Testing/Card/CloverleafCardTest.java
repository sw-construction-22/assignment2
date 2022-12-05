package Testing.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * author: daniel lutziger
 */
public class CloverleafCardTest {
    final int CURRENT_SCORE = 200;

    @Test
    public void testBonus(){
        testCloverleafCombinations(CardType.CLOVERLEAF, 0);
        cloverleafCardDrawn(CardType.CLOVERLEAF);
    }
    public void testCloverleafCombinations(CardType cardType, int adding){
        Deck bonusCardDeck = new Deck(cardType);
        Card c = bonusCardDeck.draw();
        assertEquals(cardType, c.getCardType());
        assertEquals("Drawn card: "+cardType ,c.toString());
        assertEquals(CURRENT_SCORE+adding, c.applyCardEffect(CURRENT_SCORE));
    }

    Player player;
    @BeforeEach
    public void setup() {
        setupTest();
    }

    private void setupTest() {
        player = mock(Player.class);
        when(player.roll(anyList()))
                .thenReturn(Arrays.asList(new Dice(1), new Dice(1), new Dice(4)));
    }
    private void cloverleafCardDrawn(CardType cardType) {
        GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(1), new Dice(3), new Dice(3), new Dice(3))));
        Deck straightCardDeck = new Deck(cardType);
        Card c = straightCardDeck.draw();
        gt.addCard(c);
        c.executeTurn(gt);

        assertEquals(c, gt.getDrawnCards().get(0).getCard());
        assertEquals(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice()), gt.getDice());
        assertEquals(GameState.NULL, gt.getDrawnCards().get(0).getState());
        assertEquals(Arrays.asList(), gt.getDrawnCards().get(0).getScoredPoints());
    }
}
