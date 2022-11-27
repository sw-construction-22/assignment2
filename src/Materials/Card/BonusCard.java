package Materials.Card;

import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;

import java.util.List;

public class BonusCard  extends Card implements CardRule {

    protected BonusCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public void executeRule(Player player, List<Dice> dice) {
        System.out.println("\n\nExecute " + cardType +" Bonus card");
        // throw dice
        // accept 2 tutto only and win
        int tuttoScore = 0;
        player.turnStart();
        Combination c = new Combination();
        while (tuttoScore < 1) {
            System.out.println("\nRun turn");
            player.roll(dice);
            if (c.evaluateRoll(dice).size() > 0) {
                // PATTERN FOUND (VALID)
                if (c.dicePatternSize() == dice.size()) {
                    System.out.println("TUTTO SCORED");
                    tuttoScore += 1;
                    player.addTemporary(this.getCardType().getPoints());
                    // TUTTO SCORED
                    // GIVE BACK ALL DICE
                } else {
                    // HOLD BACK DICE
                    System.out.println("Current max points from roll: " + c.dicePatternMaxPoints());
                    int index = 1;
                    for(DicePattern pattern : c.getFoundPatterns()){
                        System.out.println(index++ + " " + pattern.toString());
                    }
                    if (player.reroll()){
                        dice = player.holdBack(c.getFoundPatterns(), dice);
                    } else {
                        player.addTemporary(c.dicePatternMaxPoints());
                        player.addScore();
                        break;
                    }
                }
            } else {
                // NULL
                System.out.println("NULL");
                break;
            }
        }
    }
}