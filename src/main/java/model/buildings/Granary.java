package model.buildings;

import graphics.Graphics;
import model.exceptions.NoTypeAvailabeException;
import model.exceptions.NotEnoughException;
import model.resources.Food;
import model.resources.FoodTypes;

import java.util.HashMap;

public class Granary {

    HashMap<FoodTypes, Integer> inventory;

    public Granary() {
        inventory = new HashMap<>();
    }

    public void addFood(Food food) {
        if(!inventory.containsKey(food.getType())){
            inventory.put(food.getType(), 0);
        }
        inventory.put(food.getType(), food.getAmount() + inventory.get(food.getType()));
    }



    public Food removeFood(FoodTypes type, int amount) throws NotEnoughException, NoTypeAvailabeException {
        if(!inventory.containsKey(type)){
            throw new NoTypeAvailabeException();
        }
        int currentAmount = inventory.get(type);
        if (amount > currentAmount){
            throw new NotEnoughException();
        }
        Food food = new Food(type, amount);
        currentAmount -= amount;
        inventory.replace(type, currentAmount);
        if(currentAmount <= 0) {
            inventory.remove(type);
        }
        return food;
    }





}
