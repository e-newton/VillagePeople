package model.resources;

public class Food {
    private FoodTypes type;
    private int amount;


    public Food(FoodTypes type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public FoodTypes getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
