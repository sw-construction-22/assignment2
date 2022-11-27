package Materials.Card;

import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FireworksCard extends Card implements CardRule {

    protected FireworksCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public void executeRule(Player player, List<Dice> dice) {
        System.out.println("Execute Fireworks card");
        // throw dice
        // accept 2 tutto only and win
        Combination c = new Combination();
        while (true) {
            System.out.println("\nRun turn");
            player.roll(dice);
            if (c.evaluateRoll(dice).size() > 0) {
                // PATTERN FOUND (VALID)
                if (c.dicePatternSize() == dice.size()) {
                    System.out.println("TUTTO SCORED");
                    dice = new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice()));
                    // TUTTO SCORED
                    // GIVE BACK ALL DICE
                } else {
                    // HOLD BACK DICE
                    List<DicePattern> founds = c.getFoundPatterns();
                    for (DicePattern p : founds) {
                        player.addTemporary(p.getValue());
                        System.out.println("\n" + p.toString());
                        for (Dice d : p.getRequiredPattern()) {
                            dice.remove(d);
                            System.out.print("removed ");
                            System.out.print(d.getValue() + ", ");
                        }
                    }
                }
            } else {
                // NULL
                player.addScore();
                System.out.println("NULL");
                break;
            }
        }

    }
}
