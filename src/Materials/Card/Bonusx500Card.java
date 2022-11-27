package Materials.Card;

import Materials.Dice.Dice;
import Player.Player;

import java.util.List;

public class Bonusx500Card extends Card implements CardRule {

    protected Bonusx500Card(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public void executeRule(Player player, List<Dice> dice) {
        System.out.println("Execute Stop Card function");
    }
}
