package Materials.CardV2;

public class StopCard extends Card implements CardRule{

    protected StopCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public void executeRule() {
        System.out.println("Execute Stop Card function");
    }
}
