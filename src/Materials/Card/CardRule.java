package Materials.Card;

import Materials.Dice.Dice;
import Player.Player;

import java.util.List;

public interface CardRule {
    public void executeRule(Player player, List<Dice> dice);
}
