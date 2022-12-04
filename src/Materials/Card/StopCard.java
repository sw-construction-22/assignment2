package Materials.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Dice.Dice;
import Player.Player;

import java.util.List;

public class StopCard extends Card implements CardRule {

    protected StopCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public int applyCardEffect(int current) {
        return 0;
    }
    @Override
    public void executeRule(Player player, List<Dice> dice) {
        System.out.println("Execute Stop Card function");
    }

    @Override
    public GameTurn executeTurn(GameTurn gameTurn) {
        gameTurn.setState(GameState.NULL);
        return gameTurn;
    }
}
