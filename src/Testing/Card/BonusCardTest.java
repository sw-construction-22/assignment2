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
public class BonusCardTest {
    Player p = new Player("TestPlayer");
    final int CURRENT_SCORE = 200;

    List<Dice> dice = new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(1)));
    List<DicePattern> patterns = new ArrayList<>(Arrays.asList(DicePattern.TRIPPLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE, DicePattern.SINGLE_ONE));
    GameTurn gameTurn = new GameTurn(p, dice);

    @Test
    public void testBonus(){
        testBonusCardCombinations(CardType.BONUSx200, 200);
        testBonusCardCombinations(CardType.BONUSx300, 300);
        testBonusCardCombinations(CardType.BONUSx400, 400);
        testBonusCardCombinations(CardType.BONUSx500, 500);
        testBonusCardCombinations(CardType.BONUSx600, 600);
        bonusCardScoreTutto(CardType.BONUSx200);
        bonusCardScoreTutto(CardType.BONUSx300);
        bonusCardScoreTutto(CardType.BONUSx400);
        bonusCardScoreTutto(CardType.BONUSx500);
        bonusCardScoreTutto(CardType.BONUSx600);
        bonusCardScore(CardType.BONUSx200);
        bonusCardScore(CardType.BONUSx300);
        bonusCardScore(CardType.BONUSx400);
        bonusCardScore(CardType.BONUSx500);
        bonusCardScore(CardType.BONUSx600);
        bonusCardNullScore(CardType.BONUSx200);
        bonusCardNullScore(CardType.BONUSx300);
        bonusCardNullScore(CardType.BONUSx400);
        bonusCardNullScore(CardType.BONUSx500);
        bonusCardNullScore(CardType.BONUSx600);
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


    private void bonusCardScoreTutto(CardType cardType) {
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

    public void bonusCardScore(CardType cardType) {
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
    public void bonusCardNullScore(CardType cardType) {
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
