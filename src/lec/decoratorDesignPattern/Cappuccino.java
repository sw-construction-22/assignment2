package lec.decoratorDesignPattern;

public class Cappuccino extends Beverage {

    public Cappuccino(){
        this.description = "Cappuccino";
    }
    @Override
    public double cost(){return 2.5;}
}
