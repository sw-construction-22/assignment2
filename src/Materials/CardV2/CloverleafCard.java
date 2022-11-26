package Materials.CardV2;


public class CloverleafCard extends Card implements CardRule{

    protected CloverleafCard(CardType cardType) {assert cardType != null; super.cardType = cardType;}

    @Override
    public void executeRule() {
        System.out.println("Execute Cloverleaf Card function");
    }
}
