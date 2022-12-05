package Materials.Card;

import Game.GameTurn;

/**
 * author: daniel lutziger
 */
public interface CardRule {
    public GameTurn executeTurn(GameTurn gameTurn);
    public int applyCardEffect(int current);
}
