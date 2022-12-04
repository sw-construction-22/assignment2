package Materials.Card;

import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Card implements CardRule {
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

    protected List<DicePattern> evaluateRoll(List<Dice> thrownDices){
        assert thrownDices.size() > 0 && thrownDices.size() <= 6 && cardType != null;
        List<Dice> thrownDiceCopy = new ArrayList<>(thrownDices);
        List<DicePattern> foundPatterns = new ArrayList<>();
        // sort values
        Collections.sort(thrownDiceCopy);
        //iterate through patterns
        List<DicePattern> pats;
        pats = new ArrayList<>(Arrays.asList(DicePattern.values()));
        pats.remove(DicePattern.STRAIGHT);
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
}
