package Materials.Combinations;

import Game.GameState;
import Game.NormalState;
import Game.NullState;
import Game.TuttoState;
import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combination {
    private List<Dice> dicesForPattern;
    private List<DicePattern> foundPatterns;

    private final int hi = 10;


    public void evaluate(List<Dice> thrownDices){
        foundPatterns = evaluateRoll(thrownDices);
        GameState gs = evaluateState(thrownDices.size());

        //evaluate the roll
        //check the state
        //apply the points
    }

    /**
     * check if the roll is valid
     * @param thrownDices
     * @return the game state that the roll is valid and not null
     */
    protected List<DicePattern> evaluateRoll(List<Dice> thrownDices /* GAME STATE IS REQUIRED AS WELL */){
        assert thrownDices.size() > 0 && thrownDices.size() <= 6;
        List<Dice> localCopy = new ArrayList<>();
        dicesForPattern = new ArrayList<>();
        foundPatterns = new ArrayList<>();
        // sort values
        Collections.sort(thrownDices);
        //iterate through patterns
        for (DicePattern pattern : DicePattern.values()){
            // slice list to check if it matches with a subset
            for(int subSetStart = 0; subSetStart <= (thrownDices.size() - pattern.getRequiredPattern().size()); subSetStart++){
                List<Dice> patternCheck = localCopy.subList(subSetStart, subSetStart+pattern.getRequiredPattern().size());
                // check if pattern exists in list
                if (patternCheck.equals(pattern.getRequiredPattern())){
                    dicesForPattern.addAll(patternCheck);
                    foundPatterns.add(pattern);
                    thrownDices.removeAll(patternCheck);
                }
            }
        }
        return foundPatterns;
    }


    /**
     * 6 dice
     *  => some combination
     *
     *
     * 1 card
     *  => some event / rule
     *
     * @param numberOfThrownDice
     * @return
     */


    protected GameState evaluateState(int numberOfThrownDice){
        if (numberOfThrownDice == dicesForPattern.size()){
            return TuttoState.state();
        } else if (dicesForPattern.size() == 0){
            return NullState.state();
        } else {
            return NormalState.state();
        }
    }

    /**
     * assert that game state is tutto
     * @param card
     * @param heldBackDice
     * @param patterns
     * @return
     */
    protected int applyEffect(GameState state, Card card, List<Dice> heldBackDice, List<DicePattern> patterns) {
        assert state.equals(state.equals(TuttoState.state()));
        int score = 0;
        for (DicePattern p : patterns) {
            score += p.getValue();
        }
        if (card.getRank().equals(CardType.BONUSx200)) {
            score += CardType.BONUSx200.getPoints();
        } else if (card.getRank().equals(CardType.BONUSx300)) {
            score += CardType.BONUSx300.getPoints();
        } else if (card.getRank().equals(CardType.BONUSx400)){
            score += CardType.BONUSx400.getPoints();
        }
        return score;
    }

    public List<Dice> getDicesForPattern() {
        return dicesForPattern;
    }

    public List<DicePattern> getFoundPatterns() {
        return foundPatterns;
    }

    //evaluate roll
    // input => List<Dice>
    // game state => True / False

    //combination points
    // => List<Dice>
    // => return poitns
}
