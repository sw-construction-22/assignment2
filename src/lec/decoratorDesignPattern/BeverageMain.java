package lec.decoratorDesignPattern;

public class BeverageMain {
    public static void main(String[] args){
        Beverage b1 = new Espresso();
        Beverage b2 = new Soy(new Espresso());
        Beverage b3 = new Whip(new Soy(new Espresso()));

        System.out.println(b1.cost());
        System.out.println(b2.cost());
        System.out.println(b2.getDescription());
        System.out.println(b3.cost());
        System.out.println(b3.getDescription());
    }
}
