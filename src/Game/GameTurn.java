package Game;

import Materials.Card.Card;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameTurn {
    private Player p;
    private List<Dice> dice;
    private List<CardPointsScore> drawnCards;
    private GameState state;

    public GameTurn(Player p, List<Dice> dice) {
        this.p = p;
        this.dice = new ArrayList<>(dice);
        drawnCards = new ArrayList<>();
        state = GameState.FIRSTROLL;
    }

    public Player getP(){
        return p;
    }

    public List<Dice> getDice() {
        return dice;
    }

    public void setDice(List<Dice> dice) {
        this.dice = new ArrayList<>(dice);
    }

    public void setBackDie(Dice die){
        this.dice.remove(die);
    }
    public void tuttoScored(){
        drawnCards.get(drawnCards.size()-1).setState(GameState.TUTTO);
    }
    public void addCard(Card c){
        drawnCards.add(new CardPointsScore(c, GameState.FIRSTROLL));
    }

    public void addPoints(int points){
        drawnCards.get(drawnCards.size()-1).addScoreToCard(points);
    }

    public List<CardPointsScore> getDrawnCards() {
        return drawnCards;
    }
    public void setState(GameState state){
        drawnCards.get(drawnCards.size()-1).setState(state);
    }
    public GameState getState(){
        return drawnCards.get(drawnCards.size()-1).getState();
    }
}
