package Materials.Card;

import Game.GameTurn;
import Materials.Dice.Dice;
import Player.Player;

import java.util.List;
/**
 * author: Daniel Lutziger
 */
public class PlusMinusCard extends Card implements CardRule {

    protected PlusMinusCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public int applyCardEffect(int current) {
        return current+1000;
    }
    @Override
    public GameTurn executeTurn(GameTurn gameTurn) {
        System.out.println("no one cares yet");
        return null;
    }
}
