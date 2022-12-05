package Materials.Card;

/**
 * author: daniel lutziger
 */
public class BonusCard  extends Card implements CardRule {

    protected BonusCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public int applyCardEffect(int current) {
        return current + cardType.getPoints();
    }

}