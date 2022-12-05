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

public class StopCardTest {
    final int CURRENT_SCORE = 200;

    @Test
    public void testStop(){
        testBonusCardCombinations(CardType.STOP, 0);
    }
    public void testBonusCardCombinations(CardType cardType, int adding){
        Deck stopCardDeck = new Deck(cardType);
        Card c = stopCardDeck.draw();
        assertEquals(cardType, c.getCardType());
        assertEquals("Drawn card: "+cardType ,c.toString());
        assertEquals(adding, c.applyCardEffect(CURRENT_SCORE));
    }
}
