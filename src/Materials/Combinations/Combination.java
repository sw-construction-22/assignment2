package Materials.Combinations;

import Materials.Card.CardType;
import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Combination {
    private List<DicePattern> foundPatterns;
    /**
     * check if the roll is valid
     * @param thrownDices
     * @return the game state that the roll is valid and not null
     */
    public List<DicePattern> evaluateRoll(List<Dice> thrownDices, CardType type /* GAME STATE IS REQUIRED AS WELL */){
        assert thrownDices.size() > 0 && thrownDices.size() <= 6 && type != null;
        List<Dice> thrownDiceCopy = new ArrayList<>(thrownDices);
        foundPatterns = new ArrayList<>();
        // sort values
        Collections.sort(thrownDiceCopy);
        //iterate through patterns
        List<DicePattern> pats;
        if(type != CardType.STRAIGHT){
             pats = new ArrayList<>(Arrays.asList(DicePattern.values()));
             pats.remove(DicePattern.STRAIGHT);
        } else {
            pats = new ArrayList<>(Arrays.asList(DicePattern.STRAIGHT));
        }
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

    public int dicePatternSize() {
        int i = 0;
        int tripple5 = Collections.frequency(foundPatterns, DicePattern.TRIPPLE_FIVE);
        int tripple1 = Collections.frequency(foundPatterns, DicePattern.TRIPPLE_ONE);
        i = i - ((tripple1*3) + (tripple5 * 3));
        for (DicePattern p : foundPatterns){
            i += p.getRequiredPattern().size();
        }
        return i;
    }

    public int dicePatternMaxPoints() {
        int i = 0;
        int tripple5 = Collections.frequency(foundPatterns, DicePattern.TRIPPLE_FIVE);
        int tripple1 = Collections.frequency(foundPatterns, DicePattern.TRIPPLE_ONE);
        i = i - ((tripple1*3 * DicePattern.SINGLE_ONE.getValue()) + (tripple5 * 3 * DicePattern.SINGLE_FIVE.getValue()));
        for (DicePattern p : foundPatterns){
            i += p.getValue();
        }
        return i;
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
