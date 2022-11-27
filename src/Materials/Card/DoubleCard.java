package Materials.Card;

import Materials.Dice.Dice;
import Player.Player;

import java.util.List;

public class DoubleCard extends Card implements CardRule {

    protected DoubleCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public void executeRule(Player player, List<Dice> dice) {
        System.out.println("Execute Stop Card function");
    }

    @Override
    public int applyCardEffect(int current) {
        return current * 2;
    }
}
