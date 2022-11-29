package Game;

import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private List<Card> drawnCards;
    private Player player;
    private int highestScore;
    private int temporaryScore;
    private RollState rollState;
    private TurnState turnState;
    private Combination combination = new Combination();

    public Turn(Player player, int highestScore, Deck deck){
        this.player = player;
        this.highestScore = highestScore;
        drawnCards = new ArrayList<>();
        rollState = RollState.ROLL;
        turnState = TurnState.RUNNING;
        playTurn(deck);
    }

    public void playTurn(Deck deck){
        while(turnState.equals(TurnState.RUNNING)){
            int tuttoScore = 0;
            Card card = player.draw(deck);
            drawnCards.add(card);
            if (!card.getCardType().equals(CardType.STOP)){
                while(rollState.equals(RollState.ROLL)){
                    if (combination.evaluateRoll(dice, card.getCardType()).size() > 0) {
                        // PATTERN FOUND (VALID)
                        if (combination.dicePatternSize() == dice.size()) {
                            System.out.println("TUTTO SCORED");
                            tuttoScore+=1;
                            if(card.getCardType().equals(CardType.CLOVERLEAF)){
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
                                    if(card.getCardType().equals(CardType.PLUSMINUS)){
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
                        if (card.getCardType().equals(CardType.FIREWORKS)){
                            player.addScore();
                        }
                        //set turnstate to end
                    }
                }
            }
        }
    }

    /**
     * class for a turn
     *
     * new turn(player, leaderScore) (we pass leader score to not alter the other players)
     * while(turn.run){
     *  player.drawCard();
     *  if(draw != stop){
     *      setTurnRollState
     *      while(turnRollState){
     *          do the whole stuff from turn ... if else statemetns
     *      }
     *  }
     *  else{turn.stop}
     * }
     *
     */

    public List<Player> turn(Player player, Card c, List<Dice> dice, Combination combination){
        int tuttoScore = 0;

        while(!c.getCardType().equals(CardType.STOP)){

        }
        return null;
    }
}
