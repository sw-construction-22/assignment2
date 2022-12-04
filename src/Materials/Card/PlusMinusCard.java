package Materials.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Combinations.DicePattern;
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

    public List<Player> applyCardEffect(int current, List<Player> players, Player you) {
        for(Player p : players){
            if(p.getName() != you.getName() && you.getScore() != p.getScore()){
                p.addScore(-1000);
            }
        }
        applyCardEffect(current);
        return players;
    }
}
