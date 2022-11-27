package Player;

import Materials.Card.Card;
import Materials.Card.Deck;
import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private String name;
    private int score = 0;

    /**
     * Roll dice
     * @return rolled dice elements
     */
    public List<Dice> roll(List<Dice> dice)
    {
        for (Dice d : dice) {
            d.roll();
        }
        return dice;
    }

    /**
     * Draw card
     */
    public Card draw(Deck deck) {
        assert !deck.isEmpty();
        return deck.draw();
    }

}
