package Player;

import Materials.Card.Card;
import Materials.Card.Deck;
import Materials.Dice.Dice;

import java.util.List;

public class Player {
    private String name;
    private int temporary = 0;
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

    public void addTemporary(int temporary){
        this.temporary += temporary;
    }
    public void addScore(){
        score += temporary;
    }

    public int getScore(){
        return score;
    }

    public boolean reroll(){
        return false;
    }

    public void holdBack(){

    }

}
