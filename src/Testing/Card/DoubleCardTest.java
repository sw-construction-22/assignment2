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
public class DoubleCardTest {
    final int CURRENT_SCORE = 200;

    @Test
    public void testBonus(){
        testBonusCardCombinations(CardType.X2, 200);
        doubleCardScore(CardType.X2);
        doubleCardNullScore(CardType.X2);
        doubleCardScoreTutto(CardType.X2);
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


    private void doubleCardScoreTutto(CardType cardType) {
        GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(1))));
        Deck bonusCardDeck = new Deck(cardType);
        Card c = bonusCardDeck.draw();
        gt.addCard(c);
        c.executeTurn(gt);

        assertEquals(c, gt.getDrawnCards().get(0).getCard());
        assertEquals(Arrays.asList(new Dice(1), new Dice(1), new Dice(1)), gt.getDice());
        assertEquals(GameState.TUTTO, gt.getDrawnCards().get(0).getState());
        assertEquals(Arrays.asList(1000), gt.getDrawnCards().get(0).getScoredPoints());
    }

    public void doubleCardScore(CardType cardType) {
        GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(4))));
        Deck bonusCardDeck = new Deck(cardType);
        Card c = bonusCardDeck.draw();
        gt.addCard(c);
        c.executeTurn(gt);

        assertEquals(c, gt.getDrawnCards().get(0).getCard());
        assertEquals(Arrays.asList(new Dice(1), new Dice(1), new Dice(4)), gt.getDice());
        assertEquals(GameState.END, gt.getDrawnCards().get(0).getState());
        assertEquals(Arrays.asList(200), gt.getDrawnCards().get(0).getScoredPoints());
    }
    public void doubleCardNullScore(CardType cardType) {
        GameTurn gt = new GameTurn(player, new ArrayList<>(Arrays.asList(new Dice(4), new Dice(2), new Dice(4))));
        Deck bonusCardDeck = new Deck(cardType);
        Card c = bonusCardDeck.draw();
        gt.addCard(c);
        c.executeTurn(gt);

        assertEquals(c, gt.getDrawnCards().get(0).getCard());
        assertEquals(Arrays.asList(new Dice(4), new Dice(2), new Dice(4)), gt.getDice());
        assertEquals(GameState.NULL, gt.getDrawnCards().get(0).getState());
        assertEquals(Arrays.asList(), gt.getDrawnCards().get(0).getScoredPoints());
    }
    private void setupTest() {
        player = mock(Player.class);
        when(player.roll(anyList()))
                .thenReturn(Arrays.asList(new Dice(1), new Dice(1), new Dice(4)));
    }
}
