package model.buildings;

import model.Person;
import model.resources.Food;
import model.resources.FoodTypes;

public class Farm extends Building{
    private int foodProduced = 3;
    FoodTypes type;


    public Farm(FoodTypes type) {
        this.type = type;
    }

    public Food produceFood(){
        return new Food(type,foodProduced);
    }


    @Override
    public void use(Person p) {
        p.addFoodToInventory(produceFood());
    }
}
