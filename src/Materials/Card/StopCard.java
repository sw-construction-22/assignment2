package Materials.Card;

import Game.GameState;
import Game.GameTurn;
/**
 * author: daniel lutziger
 * representation of the stop card
 */
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
    public GameTurn executeTurn(GameTurn gameTurn) {
        gameTurn.setState(GameState.NULL);
        return gameTurn;
    }
}
