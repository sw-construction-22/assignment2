package Testing.Card;

import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlusMinusCardTest {
    final int CURRENT_SCORE = 200;

    @Test
    public void testBonus(){
        testBonusCardCombinations(CardType.STRAIGHT, 2000);
    }
    public void testBonusCardCombinations(CardType cardType, int adding){
        Deck bonusCardDeck = new Deck(cardType);
        Card c = bonusCardDeck.draw();
        assertEquals(cardType, c.getCardType());
        assertEquals("Drawn card: "+cardType ,c.toString());
        assertEquals(CURRENT_SCORE+adding, c.applyCardEffect(CURRENT_SCORE));
    }
}
