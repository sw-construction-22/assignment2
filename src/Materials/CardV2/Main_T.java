package Materials.CardV2;

public class Main_T {
    public static void main(String[] args){
        Deck d = new Deck();
        d.shuffle();
        d.draw().executeRule();
        d.isEmpty();
    }
}
