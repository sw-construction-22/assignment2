package Materials.Card;

import Materials.Card.Card;
import Materials.Card.CardRule;
import Materials.Card.CardType;
import Materials.Dice.Dice;
import Player.Player;

import java.util.List;

public class StopCard extends Card implements CardRule {

    protected StopCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public void executeRule(Player player, List<Dice> dice) {
        System.out.println("Execute Stop Card function");
    }
}