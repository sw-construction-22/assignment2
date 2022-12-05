package Materials.Card;


import Game.GameState;
import Game.GameTurn;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * author: daniel lutziger
 */
public class CloverleafCard extends Card implements CardRule {

    protected CloverleafCard(CardType cardType) {assert cardType != null; super.cardType = cardType;}

    @Override
    public int applyCardEffect(int current) {
        return current;
    }

    @Override
    public GameTurn executeTurn(GameTurn gameTurn) {
        int tuttoScore = 0;
        System.out.println("Run turn");
        do {
            gameTurn.getP().roll(gameTurn.getDice());
            List<DicePattern> patterns = evaluateRoll(gameTurn.getDice());
            if (patterns.size() > 0) {
                // PATTERN FOUND (VALID)
                if (DicePattern.dicePatternSize(patterns) == gameTurn.getDice().size()) {
                    System.out.println("Tutto scored");
                    tuttoScore += 1;
                    if (tuttoScore == 2){
                        gameTurn.setState(GameState.CLOVERLEAF);
                        System.out.println("WIN");
                        return gameTurn;
                    } else {
                        gameTurn.setDice(new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice())));
                    }

                } else {
                    {
                        int index = 1;
                        for (DicePattern pattern : patterns) {
                            System.out.println(index++ + " " + pattern.toString());
                        }
                        List<Dice> diceToLayBack = gameTurn.getP().holdBack(patterns, gameTurn.getDice());
                        for(Dice current : diceToLayBack){
                            gameTurn.setBackDie(current);
                        }
                        gameTurn.setState(GameState.REROLL);
                    }
                }
            } else {
                // NULL
                gameTurn.setState(GameState.NULL);
                return gameTurn;
            }
        }while(tuttoScore < 2);
        return gameTurn;
    }
}
