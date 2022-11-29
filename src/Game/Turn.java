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
    private List<Dice> dice;

    public Turn(Player player, int highestScore, Deck deck, List<Dice> dice){
        this.dice = dice;
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
            System.out.println("Card drawn: " + card.getCardType());
            drawnCards.add(card);
            if (!card.getCardType().equals(CardType.STOP)){
                while(rollState.equals(RollState.ROLL)){
                    dice = player.roll(dice);
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
                                    // set state to end roll
                                    // break
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
                                turnState = TurnState.STOP;
                                break;
                            }
                        }
                    } else {
                        rollState = RollState.NULL;
                        // NULL scored
                        if (card.getCardType().equals(CardType.FIREWORKS)){
                            player.addScore();
                        }
                        //set turnstate to end
                    }
                }
            }
            else {
                turnState = TurnState.STOP;
            }
        }
    }

}
