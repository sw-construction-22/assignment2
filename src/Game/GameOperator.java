package Game;

import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;

import java.util.*;

public class GameOperator {
    private List<Player> players;
    private Deck deck;
    private List<Dice> dice;
    private Combination combination;
    public GameOperator() {
        deck = new Deck();
        deck.isEmpty();

        // Input how many players
        players = new ArrayList<>(Arrays.asList(new Player("Hansel"), new Player("Gretel"), new Player("Arnold")));
        // sort players
        dice = new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice()));

        // this stuff should probably be in a subclass of either cards or dice or something game operator or so..
        combination = new Combination();
        // start game aka turns
        // while game state or something
        // game turn
        getAlphabetical();
        for (int i = 0; i < 10; i++) {
            for (Player x : players){
                System.out.println(x.getName());
            }

            for (Player p : players) {
                System.out.println("-------------- START TURN ------------------");
                System.out.println("Player's move: " + p.getName());
                System.out.println("Player's score: " + p.getScore());
                Turn current = new Turn(p, getGameLeader().get(0).getScore(), deck, dice);
                System.out.println("-------------- END TURN ------------------");
                /**
                 * apply the effects of the cards
                 */
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


    /**
     * probably return the game state victory or something
     * REGULAR FLOW FOR BONUS, DOUBLE CARDS
     *
     * IRREGULAR FLOW HAS FIREWORKS CARD
     * IRREGULAR FLOW HAS PLUS MINUS CARD
     * IRREGULAR FLOW HAS STRAIGHT CARD
     *
     */

    public void endTurn(Player player){
        player.addTemporary(combination.dicePatternMaxPoints());
        player.addScore();
    }

    public void regularThrow(Player player){
        System.out.println("Current max points from roll: " + combination.dicePatternMaxPoints());
        int index = 1;
        for(DicePattern pattern : combination.getFoundPatterns()){
            System.out.println(index++ + " " + pattern.toString());
        }
        if (player.reroll()){
            dice = player.holdBack(combination.getFoundPatterns(), dice);
        } else {
            endTurn(player);
        }
    }
}
