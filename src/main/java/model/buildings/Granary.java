package model.buildings;

import graphics.Graphics;
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





}
