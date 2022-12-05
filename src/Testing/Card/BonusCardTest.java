package Testing.Card;

import Game.GameTurn;
import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }
    public void testBonusCardCombinations(CardType cardType, int adding){
        Deck bonusCardDeck = new Deck(cardType);
        Card c = bonusCardDeck.draw();
        assertEquals(cardType, c.getCardType());
        assertEquals("Drawn card: "+cardType ,c.toString());
        assertEquals(CURRENT_SCORE+adding, c.applyCardEffect(CURRENT_SCORE));
    }
}
