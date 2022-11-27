import Game.GameState;
import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Deck d = new Deck();
        d.isEmpty();

        // Input how many players
        List<Player> players = new ArrayList<>(Arrays.asList(new Player(), new Player()));
        // sort players
        List<Dice> dice = new ArrayList<>(Arrays.asList( new Dice(),  new Dice(),  new Dice(), new Dice(), new Dice(), new Dice()));

        // this stuff should probably be in a subclass of either cards or dice or something game operator or so..
        Combination c = new Combination();
        // start game aka turns
        // while game state or something
            // game turn
        while(true){
            for(Player p : players){
                Card card = p.draw(d);
                if(card.getCardType() != CardType.STOP){

                }
                // throw dice
                // accept 2 tutto only and win
                int tuttoScore = 0;
                p.turnStart();
            }
        }



    }
}