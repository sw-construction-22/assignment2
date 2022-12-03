package lec.decoratorDesignPattern;

public class Soy extends CondimentDecorator{
    private Beverage beverage;
    public Soy(Beverage beverage){
        this.beverage = beverage;
    }
    @Override
    public double cost() {
        return this.beverage.cost() + 0.2;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + " with Soy";
    }
}
