package Game;

import Materials.Card.Card;

import java.util.ArrayList;
import java.util.List;
/**
 * author: daniel lutziger
 */
public class CardPointsScore {
    private Card card;
    private GameState state;
    private List<Integer> scoredPoints;

    public CardPointsScore(Card c, GameState state) {
        card = c;
        this.state = state;
        scoredPoints = new ArrayList<>();
    }

    public void addScoreToCard(int e){
        scoredPoints.add(e);
    }

    public Card getCard() {
        return card;
    }


    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public List<Integer> getScoredPoints() {
        return scoredPoints;
    }
}
