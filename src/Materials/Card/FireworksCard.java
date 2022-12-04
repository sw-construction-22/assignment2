package Materials.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
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
    

    @Override
    public GameTurn executeTurn(GameTurn gameTurn) {
        System.out.println("Run turn");
        do {
            gameTurn.getP().roll(gameTurn.getDice());
            List<DicePattern> patterns = evaluateRoll(gameTurn.getDice());
            if (patterns.size() > 0) {
                // PATTERN FOUND (VALID)
                if (DicePattern.dicePatternSize(patterns) == gameTurn.getDice().size()) {
                    System.out.println("Current max points from roll: " + DicePattern.dicePatternMaxPoints(patterns));
                    System.out.println("Tutto scored");
                    gameTurn.addPoints(DicePattern.dicePatternMaxPoints(patterns));
                    gameTurn.setState(GameState.TUTTO);
                    return gameTurn;
                } else {
                    {
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
                }
            } else {
                // NULL
                gameTurn.setState(GameState.END);
                return gameTurn;
            }
        }while(gameTurn.getState().equals(GameState.REROLL));
        return gameTurn;
    }
}
