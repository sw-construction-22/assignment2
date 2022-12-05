package Materials.Card;

import Game.GameTurn;

/**
 * author: daniel lutziger
 * each card implements those mehtods
 */
public interface CardRule {
    public GameTurn executeTurn(GameTurn gameTurn);
    public int applyCardEffect(int current);
}
