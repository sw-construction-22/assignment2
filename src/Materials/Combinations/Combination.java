package Materials.Combinations;

import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combination {

    private List<Dice> dicesForPattern;
    private List<DicePattern> foundPatterns;
    /**
     * check if the roll is valid
     * @param thrownDices
     * @return the game state that the roll is valid and not null
     */
    public boolean evaluateRoll(List<Dice> thrownDices /* GAME STATE IS REQUIRED AS WELL */){
        assert thrownDices.size() > 0 && thrownDices.size() <= 6;
        List<Dice> thrownDiceCopy = new ArrayList<>(thrownDices);
        dicesForPattern = new ArrayList<>();
        foundPatterns = new ArrayList<>();
        // sort values
        Collections.sort(thrownDiceCopy);
        //iterate through patterns
        for (DicePattern pattern : DicePattern.values()){
            // slice list to check if it matches with a subset
            for(int subSetStart = 0; subSetStart <= (thrownDiceCopy.size() - pattern.getRequiredPattern().size()); subSetStart++){
                List<Dice> patternCheck = thrownDiceCopy.subList(subSetStart, subSetStart+pattern.getRequiredPattern().size());
                // check if pattern exists in list
                if (patternCheck.equals(pattern.getRequiredPattern())){
                    subSetStart -= 1;
                    dicesForPattern.addAll(patternCheck);
                    foundPatterns.add(pattern);
                    for(Dice current : pattern.getRequiredPattern()){
                        thrownDiceCopy.remove(current);
                    }
                }
            }
        }
        return foundPatterns.size() > 0;
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
