import Materials.Card.Deck;

public class Main {
    public static void main(String[] args){
        Deck d = new Deck();
        d.draw().executeRule();
        d.isEmpty();
    }
}