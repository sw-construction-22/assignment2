package Game;

import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Combinations.Combination;
import Materials.Dice.Dice;
import Player.Player;

import java.util.*;

public class GameOperator {
    private List<Player> players;
    private Deck deck;
    private List<Dice> dice;
    private Combination combination;
    public GameOperator(){
        deck = new Deck();
        deck.isEmpty();

        // Input how many players
        players = new ArrayList<>(Arrays.asList(new Player("Hansel"), new Player("Gretel"), new Player("Arnold")));
        // sort players
        dice = new ArrayList<>(Arrays.asList( new Dice(),  new Dice(),  new Dice(), new Dice(), new Dice(), new Dice()));

        // this stuff should probably be in a subclass of either cards or dice or something game operator or so..
        combination = new Combination();
        // start game aka turns
        // while game state or something
        // game turn
        getAlphabetical();
        for(int i = 0; i<10; i++){

            for(Player p : players){
                Card card = p.draw(deck);
                System.out.println("Card drawn: " + card.getCardType());
                if(card.getCardType() != CardType.STOP /*&&
                        card.getCardType() != CardType.CLOVERLEAF &&
                        card.getCardType() != CardType.PLUSMINUS*/) {
                    dice = p.roll(dice);
                    p.turn(card, dice, combination);
                } else {
                    // special turn for cloverleaf and plus minus (stack stuff)
                }
            }
        }
    }

    public List<Player> getAlphabetical(){
        Comparator<Player> byName = Comparator.comparing(Player::getName);
        Collections.sort(players, byName);
        return players;
    }
    public List<Player> getGameLeader(){
        Comparator<Player> byScore = Comparator.comparing(Player::getScore);
        Collections.sort(players, byScore);
        Collections.reverse(players); //highest player on top
        List<Player> leaders = new ArrayList<>();
        for (Player p : players){
            if (p.getScore() == players.get(0).getScore()){
                leaders.add(p);
            }
        }
        getAlphabetical();
        return leaders;
    }

}
