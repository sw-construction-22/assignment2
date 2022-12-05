package Testing.Card;

import Game.GameTurn;
import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Card.StraightCard;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StraightCardTest {
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
