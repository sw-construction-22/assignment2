package Materials.Card;

import Game.GameTurn;


public interface CardRule {
    public GameTurn executeTurn(GameTurn gameTurn);
    public int applyCardEffect(int current);
}
