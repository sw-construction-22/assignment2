package Materials.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * author: daniel lutziger
 */
public class StraightCard extends Card implements CardRule {

    protected StraightCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    /**
     * override the straight patttern validation
     * 1-6 are valid but only once
     * @param thrownDices the dice thrown
     * @param heldBackPats the dice which were already held back
     * @return the possible patterns for the user to hold back
     */
    public List<DicePattern> evaluateRoll(List<Dice> thrownDices, List<DicePattern> heldBackPats){
        assert thrownDices.size() > 0 && thrownDices.size() <= 6 && cardType != null;
        List<Dice> thrownDiceCopy = new ArrayList<>(thrownDices);
        List<DicePattern> foundPatterns = new ArrayList<>();
        // sort values
        Collections.sort(thrownDiceCopy);
        //iterate through patterns
        List<DicePattern> pats;
        pats = new ArrayList<>(Arrays.asList(DicePattern.STRAIGHT, DicePattern.SINGLE_ONE, DicePattern.SINGLE_FIVE, DicePattern.SINGLE_TWO, DicePattern.SINGLE_THREE, DicePattern.SINGLE_FOUR, DicePattern.SINGLE_SIX));
        pats.removeAll(heldBackPats);
        for (DicePattern pattern : pats){
            // slice list to check if it matches with a subset
            for(int subSetStart = 0; subSetStart <= (thrownDiceCopy.size() - pattern.getRequiredPattern().size()); subSetStart++){
                List<Dice> patternCheck = thrownDiceCopy.subList(subSetStart, subSetStart+pattern.getRequiredPattern().size());
                // check if pattern exists in list
                if (patternCheck.equals(pattern.getRequiredPattern())){
                    subSetStart -= 1;
                    foundPatterns.add(pattern);
                    thrownDiceCopy.removeAll(pattern.getRequiredPattern());
                }
            }
        }
        return foundPatterns;
    }

    @Override
    public int applyCardEffect(int current) {
        return current + 2000;
    }

    /**
     *
     * @param gameTurn the game turn the user currently has
     * @return the turn with either a tutto or a null
     */
    @Override
    public GameTurn executeTurn(GameTurn gameTurn) {
        System.out.println("Run turn");
        List<DicePattern> heldBack = new ArrayList<>();
        do {
            gameTurn.getP().roll(gameTurn.getDice());
            List<DicePattern> patterns = evaluateRoll(gameTurn.getDice(), heldBack);
            if (patterns.size() > 0) {
                // PATTERN FOUND (VALID)
                if (DicePattern.dicePatternSize(patterns) == gameTurn.getDice().size()) {
                    System.out.println("Tutto scored");
                    gameTurn.addPoints(0);
                    gameTurn.setState(GameState.TUTTO);
                    return gameTurn;
                } else {
                    int index = 1;
                    for (DicePattern pattern : patterns) {
                        System.out.println(index++ + " " + pattern.toString());
                    }
                    List<Dice> diceToLayBack = gameTurn.getP().holdBack(patterns, gameTurn.getDice());
                    heldBack.addAll(evaluateRoll(diceToLayBack, heldBack));
                    for(Dice current : diceToLayBack){
                        gameTurn.setBackDie(current);
                    }
                    gameTurn.setState(GameState.REROLL);
                }
            } else {
                // NULL
                gameTurn.setState(GameState.NULL);
                return gameTurn;
            }
        }while(gameTurn.getState().equals(GameState.REROLL));
        return gameTurn;
    }
}
