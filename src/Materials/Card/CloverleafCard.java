package Materials.Card;


import Materials.Card.Card;
import Materials.Card.CardRule;
import Materials.Card.CardType;
import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloverleafCard extends Card implements CardRule {

    protected CloverleafCard(CardType cardType) {assert cardType != null; super.cardType = cardType;}

    @Override
    public int applyCardEffect(int current) {
        return 0;
    }
    /**
     * Make method easier for testing
     * @param player
     * @param dice
     */
    @Override
    public void executeRule(Player player, List<Dice> dice) {
        System.out.println("\n\nExecute Cloverleaf Card function");
        // throw dice
        // accept 2 tutto only and win
        int tuttoScore = 0;
        Combination c = new Combination();
        while (tuttoScore < 2) {
            System.out.println("\nRun turn");
            player.roll(dice);
            if (c.evaluateRoll(dice, this.cardType).size() > 0) {
                // PATTERN FOUND (VALID)
                if (c.dicePatternSize() == dice.size()) {
                    System.out.println("TUTTO SCORED");
                    tuttoScore += 1;
                    dice = new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice()));
                    // TUTTO SCORED
                    // GIVE BACK ALL DICE
                } else {
                    // HOLD BACK DICE
                    int index = 1;
                    for(DicePattern pattern : c.getFoundPatterns()){
                        System.out.println(index++ + " " + pattern.toString());
                    }
                    dice = player.holdBack(c.getFoundPatterns(), dice);
                }
            } else {
                // NULL
                System.out.println("NULL");
                break;
            }
            if (tuttoScore == 2) {
                System.out.println("WIN");
            }
        }
    }
}
