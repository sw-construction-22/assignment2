package Materials.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * author: daniel lutziger
 * basic implementation of a card
 */
public abstract class Card implements CardRule {
    /**
     * flyweight pattern applied in this class
     */
    protected static final Card[] CARDS = new Card[CardType.values().length];
    protected CardType cardType;

    public static Card get(CardType pCardType) {
        assert pCardType != null;
        return CARDS[pCardType.ordinal()];
    }

    /**
     * @return The the card type.
     */
    public CardType getCardType()
    {
        return cardType;
    }

    public String toString(){
        return "Drawn card: " + cardType.toString();
    }

    /**
     * basic evaluation of a card and the containing roll
     * - this method is overwritten for the straight card
     * @param thrownDices the list of the dice the user has thrown
     * @return the different patterns which apply to this card
     */
    public List<DicePattern> evaluateRoll(List<Dice> thrownDices){
        assert thrownDices.size() > 0 && thrownDices.size() <= 6 && cardType != null;
        List<Dice> thrownDiceCopy = new ArrayList<>(thrownDices);
        List<DicePattern> foundPatterns = new ArrayList<>();
        // sort values
        Collections.sort(thrownDiceCopy);
        //iterate through patterns
        List<DicePattern> pats;
        pats = new ArrayList<>(Arrays.asList(DicePattern.values()));
        //as the dicepattern contain ALL the possible patterns, these have to be removed as they are specifically for a straight
        pats.remove(DicePattern.STRAIGHT);
        pats.remove(DicePattern.SINGLE_TWO);
        pats.remove(DicePattern.SINGLE_THREE);
        pats.remove(DicePattern.SINGLE_FOUR);
        pats.remove(DicePattern.SINGLE_SIX);
        for (DicePattern pattern : pats){
            // slice list to check if it matches with a subset
            for(int subSetStart = 0; subSetStart <= (thrownDiceCopy.size() - pattern.getRequiredPattern().size()); subSetStart++){
                List<Dice> patternCheck = thrownDiceCopy.subList(subSetStart, subSetStart+pattern.getRequiredPattern().size());
                // check if pattern exists in list
                if (patternCheck.equals(pattern.getRequiredPattern())){
                    subSetStart -= 1;
                    foundPatterns.add(pattern);
                    for(Dice current : pattern.getRequiredPattern()){
                        thrownDiceCopy.remove(current);
                        //if a user scores a tripple 1 or 5 the patterns have to show that as well
                        // the user can, if he wants to, just hold back a single one instead of a tripple one
                        // this is what this method is for
                        if(pattern.equals(DicePattern.TRIPPLE_ONE)){
                            foundPatterns.add(DicePattern.SINGLE_ONE);
                        } else if (pattern.equals(DicePattern.TRIPPLE_FIVE)){
                            foundPatterns.add(DicePattern.SINGLE_FIVE);
                        }
                    }
                }
            }
        }
        return foundPatterns;
    }

    /**
     * this method is to execute a regular game turn for e.g. a bonus card or a double card
     * - the method is overwritten for the cloverleaf, straight, stop, and fireworks card
     * @param gameTurn the game turn the user currently has
     * @return the game turn object which belongs to this card
     */
    public GameTurn executeTurn(GameTurn gameTurn) {
        System.out.println("Run turn");
        do {
            gameTurn.getP().roll(gameTurn.getDice());
            List<DicePattern> patterns = evaluateRoll(gameTurn.getDice());
            if (patterns.size() > 0) {
                // PATTERN FOUND (VALID)
                gameTurn = checkForTuttoOrEnd(gameTurn, patterns);
            } else {
                // NULL
                gameTurn.setState(GameState.NULL);
                return gameTurn;
            }
        }while(gameTurn.getState().equals(GameState.REROLL));
        return gameTurn;
    }

    /**
     * abstraction of iterative code
     * - the evaluation if the user scored a tutto in the current turn (and draw a new card) or if he wants to reroll with the current card
     * @param gameTurn the user passed
     * @param patterns the user has thrown
     * @return the current, modified game turn
     */
    public GameTurn checkForTuttoOrEnd(GameTurn gameTurn, List<DicePattern> patterns){
        if (DicePattern.dicePatternSize(patterns) == gameTurn.getDice().size()) {
            // if a tutto is thrown, this card allows the user to draw a new card
            System.out.println("Current max points from roll: " + DicePattern.dicePatternMaxPoints(patterns));
            System.out.println("Tutto scored");
            gameTurn.addPoints(DicePattern.dicePatternMaxPoints(patterns));
            gameTurn.setState(GameState.TUTTO);
            return gameTurn;
        } else {
            // if a pattern is thrown, this card allows the user to end the turn or reroll
            System.out.println("Current max points from roll: " + DicePattern.dicePatternMaxPoints(patterns));
            int index = 1;
            for (DicePattern pattern : patterns) {
                System.out.println(index++ + " " + pattern.toString());
            }
            if (gameTurn.getP().reroll()) {
                List<Dice> diceToLayBack = gameTurn.getP().holdBack(patterns, gameTurn.getDice());
                gameTurn.addPoints(DicePattern.dicePatternMaxPoints(evaluateRoll(diceToLayBack))); //evaluate the points for the laid back dice
                for(Dice current : diceToLayBack){
                    gameTurn.setBackDie(current);
                }
                gameTurn.setState(GameState.REROLL);
            } else {
                gameTurn.addPoints(DicePattern.dicePatternMaxPoints(patterns));
                gameTurn.setState(GameState.END);
                return gameTurn;
            }
        }
        return gameTurn;
    }
}
