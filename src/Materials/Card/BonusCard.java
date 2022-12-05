package Materials.Card;

/**
 * author: daniel lutziger
 * representation of a bonus card
 */
public class BonusCard  extends Card implements CardRule {

    protected BonusCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    /**
     *
     * @param current points of the user
     * @return the amount the bonus card will add to the user's score (200,300,400,500,600)
     */
    @Override
    public int applyCardEffect(int current) {
        return current + cardType.getPoints();
    }

}