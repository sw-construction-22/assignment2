package Materials.Card;

import Materials.Dice.Dice;
import Player.Player;
import Game.GameTurn;

import java.util.List;

public interface CardRule {
    public void executeRule(Player player, List<Dice> dice);
    public GameTurn executeTurn(GameTurn gameTurn);
    public int applyCardEffect(int current);
}
