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
 * author: Daniel Lutziger
 */
public class FireworksCard extends Card implements CardRule {

    protected FireworksCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public int applyCardEffect(int current) {
        return current;
    }

    public List<DicePattern> evaluateRoll(List<Dice> thrownDices){
        assert thrownDices.size() > 0 && thrownDices.size() <= 6 && cardType != null;
        List<Dice> thrownDiceCopy = new ArrayList<>(thrownDices);
        List<DicePattern> foundPatterns = new ArrayList<>();
        // sort values
        Collections.sort(thrownDiceCopy);
        //iterate through patterns
        List<DicePattern> pats;
        pats = new ArrayList<>(Arrays.asList(DicePattern.values()));
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
                    }
                }
            }
        }
        return foundPatterns;
    }
    @Override
    public GameTurn executeTurn(GameTurn gameTurn) {
        System.out.println("Run turn");
        do {
            gameTurn.getP().roll(gameTurn.getDice());
            List<DicePattern> patterns = evaluateRoll(gameTurn.getDice());
            if (patterns.size() > 0) {
                // PATTERN FOUND (VALID)
                //add points for all valid patterns => no new draw card
                if (DicePattern.dicePatternSizeFireworks(patterns) == gameTurn.getDice().size()) {
                    System.out.println("Tutto scored");
                    gameTurn.setDice(new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice())));
                } else {
                    {
                        System.out.println("Current max points from roll: " + DicePattern.dicePatternMaxPointsFireworks(patterns));
                        int index = 1;
                        for (DicePattern pattern : patterns) {
                            System.out.println(index++ + " " + pattern.toString());
                        }
                        List<Dice> diceToLayBack = new ArrayList<>();
                        for(DicePattern p : patterns) {
                            diceToLayBack.addAll(p.getRequiredPattern());
                        }
                        gameTurn.addPoints(DicePattern.dicePatternMaxPointsFireworks(evaluateRoll(diceToLayBack)));
                        for(Dice current : diceToLayBack){
                            gameTurn.setBackDie(current);
                        }
                        gameTurn.setState(GameState.REROLL);
                    }
                }
                //gameTurn = checkForTuttoOrEnd(gameTurn, patterns);
            } else {
                // NULL
                gameTurn.setState(GameState.END);
                return gameTurn;
            }
        }while(gameTurn.getState().equals(GameState.REROLL));
        return gameTurn;
    }
}
