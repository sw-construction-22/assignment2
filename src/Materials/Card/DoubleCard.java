package Materials.Card;

/**
 * author: daniel lutziger
 */
public class DoubleCard extends Card implements CardRule {

    protected DoubleCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public int applyCardEffect(int current) {
        return current * 2;
    }

}
