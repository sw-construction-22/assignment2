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
                    turn(p, card, dice, combination);
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


    /**
     * probably return the game state victory or something
     * REGULAR FLOW FOR BONUS, DOUBLE CARDS
     *
     * IRREGULAR FLOW HAS FIREWORKS CARD
     * IRREGULAR FLOW HAS PLUS MINUS CARD
     * IRREGULAR FLOW HAS STRAIGHT CARD
     *
     */

    /**
     * TODO: return manipulated players or something
     * @param c
     * @param dice
     * @param combination
     */
    public List<Player> turn(Player player, Card c, List<Dice> dice, Combination combination){
        int tuttoScore = 0;

        while(!c.getCardType().equals(CardType.STOP)){
            if (combination.evaluateRoll(dice, c.getCardType()).size() > 0) {
                // PATTERN FOUND (VALID)
                if (combination.dicePatternSize() == dice.size()) {
                    System.out.println("TUTTO SCORED");
                    tuttoScore+=1;
                    if(c.getCardType().equals(CardType.CLOVERLEAF)){
                        if(tuttoScore == 2){
                            // GAME WIN apply cloverleaf effect
                            System.out.println("Game won");
                            break;
                        } else {
                            // return dice and reroll
                            // GIVE BACK ALL DICE
                        }
                    } else {
                        if (player.reroll()) {
                            //give back all dice
                            //draw a new card
                            // dice = ;
                        } else {
                            // apply the effects foreach drawn card with the points scored for it
                            if(c.getCardType().equals(CardType.PLUSMINUS)){
                                //subtract from first player
                            }

                        }
                    }
                } else {
                    // HOLD BACK DICE
                    System.out.println("Current max points from roll: " + combination.dicePatternMaxPoints());
                    int index = 1;
                    for(DicePattern pattern : combination.getFoundPatterns()){
                        System.out.println(index++ + " " + pattern.toString());
                    }
                    if (player.reroll()){
                        dice = player.holdBack(combination.getFoundPatterns(), dice);
                    } else {
                        player.addTemporary(combination.dicePatternMaxPoints());
                        player.addScore();
                        break;
                    }
                }
            } else {
                // NULL scored
                if (c.getCardType().equals(CardType.FIREWORKS)){
                    player.addScore();
                }
                //set turnstate to end
            }
        }
        return null;
    }
}
