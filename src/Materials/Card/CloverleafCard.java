package Materials.Card;


import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloverleafCard extends Card implements CardRule{

    protected CloverleafCard(CardType cardType) {assert cardType != null; super.cardType = cardType;}

    @Override
    public void executeRule() {
        System.out.println("Execute Cloverleaf Card function");
        // throw dice
        // accept 2 tutto only and win
        int tuttoScore = 0;
        List<Dice> dice = new ArrayList<>(Arrays.asList( new Dice(),  new Dice(),  new Dice(), new Dice(), new Dice(), new Dice()));
        Combination c = new Combination();
        while (tuttoScore < 2){
            System.out.println("\nRun turn");
            for(Dice d : dice){
                d.roll();
                System.out.print(d.getValue() + ", ");
            }
            if (c.evaluateRoll(dice)) {
                List<DicePattern> founds = c.getFoundPatterns();
                for(DicePattern p : founds){
                    System.out.println("\n" + p.toString());
                    for(Dice d : p.getRequiredPattern()){
                        dice.remove(d);
                        System.out.print("removed ");
                        System.out.print(d.getValue() + ", ");
                    }
                }
                // remove dice from arraylist

            } else {
                System.out.println("NULL");
                break;
            }
        }
        // while tuttoScore < 2 || null rolled
        // roll dice
        // evaluate roll
        // if tutto
        // hold back
        //
    }
}
