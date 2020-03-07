package model.buildings;

import graphics.Graphics;
import model.GridSquare;
import model.Person;
import model.exceptions.NoTypeAvailabeException;
import model.exceptions.NotEnoughException;
import model.resources.Food;
import model.resources.FoodTypes;

import java.awt.*;
import java.util.HashMap;

public class Granary extends Building{
    private static final Color GRANARY_COLOUR = Color.GRAY;
    HashMap<FoodTypes, Integer> inventory;

    public Granary(GridSquare gridSquare) {
        inventory = new HashMap<>();
        this.colour = GRANARY_COLOUR;
        this.gridSquare = gridSquare;
        gridSquare.setBuilding(this);
    }

    public Granary() {
        inventory = new HashMap<>();
        this.colour = GRANARY_COLOUR;
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

    public HashMap<FoodTypes, Integer> getInventory() {
        return inventory;
    }

    public boolean hasFood(){
        return !inventory.isEmpty();
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void releaseWorkers() {

    }

    @Override
    public void use(Person p) {
        addFood(p.removeFoodFromInventory());
        System.out.println("Granary: " +  inventory);
    }

}
