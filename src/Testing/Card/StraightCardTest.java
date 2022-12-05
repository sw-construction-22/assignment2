package Testing.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Card.StraightCard;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * author: daniel lutziger
 */
public class StraightCardTest {
    final int CURRENT_SCORE = 200;

    @Test
    public void testBonus(){
        testBonusCardCombinations(CardType.STRAIGHT, 2000);
        straightCardDrawn(CardType.STRAIGHT);
    }
    public void testBonusCardCombinations(CardType cardType, int adding){
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
    }
    private void straightCardDrawn(CardType cardType) {
        GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(1), new Dice(2), new Dice(3), new Dice(4), new Dice(5), new Dice(6))));
        Deck straightCardDeck = new Deck(cardType);
        Card c = straightCardDeck.draw();
        gt.addCard(c);
        c.executeTurn(gt);

        assertEquals(c, gt.getDrawnCards().get(0).getCard());
        assertEquals(Arrays.asList(new Dice(1), new Dice(2), new Dice(3), new Dice(4), new Dice(5), new Dice(6)), gt.getDice());
        assertEquals(GameState.TUTTO, gt.getDrawnCards().get(0).getState());
        assertEquals(Arrays.asList(0), gt.getDrawnCards().get(0).getScoredPoints());
    }
}
