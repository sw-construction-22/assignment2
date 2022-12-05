package Testing.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Card.Card;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * author: daniel lutziger
 */
public class StopCardTest {
    final int CURRENT_SCORE = 200;

    @Test
    public void testStop(){
        testBonusCardCombinations(CardType.STOP, 0);
        stopCardDrawn(CardType.STOP);
    }
    public void testBonusCardCombinations(CardType cardType, int adding){
        Deck stopCardDeck = new Deck(cardType);
        Card c = stopCardDeck.draw();
        assertEquals(cardType, c.getCardType());
        assertEquals("Drawn card: "+cardType ,c.toString());
        assertEquals(adding, c.applyCardEffect(CURRENT_SCORE));
    }
    Player player;
    @BeforeEach
    public void setup() {
        setupTest();
    }

    private void setupTest() {
        player = mock(Player.class);
    }
    private void stopCardDrawn(CardType cardType) {
        GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(1))));
        Deck stopCardDeck = new Deck(cardType);
        Card c = stopCardDeck.draw();
        gt.addCard(c);
        c.executeTurn(gt);

        assertEquals(c, gt.getDrawnCards().get(0).getCard());
        assertEquals(Arrays.asList(new Dice(1), new Dice(1), new Dice(1)), gt.getDice());
        assertEquals(GameState.NULL, gt.getDrawnCards().get(0).getState());
        assertEquals(Arrays.asList(), gt.getDrawnCards().get(0).getScoredPoints());
    }

}
