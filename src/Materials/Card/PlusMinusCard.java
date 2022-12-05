package Materials.Card;


import Player.Player;

import java.util.List;
/**
 * author: Daniel Lutziger
 * representation of the plus minus card
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

    /**
     * special method for the plus minus card where the points will be subtracted
     * @param current current score of the user
     * @param players all the leading players
     * @param you the player who drew the plusminus card
     * @return the players for whom the score will be reduced
     */
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
