package Materials.Card;

/**
 * Factory design pattern
 */
public class CardFactory {
    public Card getCard(CardType cardType){

         if (cardType.equals(CardType.STOP)){
             return new StopCard(cardType);
         } else if (cardType.equals(CardType.CLOVERLEAF)){
             return new CloverleafCard(cardType);
         } else if (cardType.equals(CardType.BONUSx200)){
             return new BonusCard(cardType);
         } else if (cardType.equals(CardType.BONUSx300)){
             return new BonusCard(cardType);
         } else if (cardType.equals(CardType.BONUSx400)){
              return new BonusCard(cardType);
         } else if (cardType.equals(CardType.BONUSx500)){
              return new BonusCard(cardType);
         } else if (cardType.equals(CardType.BONUSx600)){
              return new BonusCard(cardType);
         } else if (cardType.equals(CardType.X2)){
              return new DoubleCard(cardType);
         } else if (cardType.equals(CardType.PLUSMINUS)){
              return new PlusMinusCard(cardType);
         } else if (cardType.equals(CardType.FIREWORKS)){
              return new FireworksCard(cardType);
         } else if (cardType.equals(CardType.STRAIGHT)){
              return new StraightCard(cardType);
         }
        return null;
    }
}
