package model;

import model.buildings.Building;
import model.buildings.House;
import model.resources.Food;

public class Person {
    public static final int MAX_INVENTORY = 5;
    private Building destination;
    private Food inventory;
    private House house;
    private int hunger = 100;
    private int energy = 100;

    public Person(){
        this.destination = null;
        this.inventory = null;
        this.destination = null;
        this.house = null;
    }


    public void addHouse(House house) {
        if(this.house == null) {
            this.house = house;
            house.addOccupant(this);
        }
    }

    public void removeHouse(House house) {
        if(house != null) {
            this.house = null;
            house.removeOccupant(this);
        }
    }

    public void addFoodToInventory(Food f){
        this.inventory = f;
    }

    public Food removeFoodFromInventory(){
        Food f = this.inventory;
        this.inventory = null;
        return f;
    }

    public void step(){

    }

    public void die(){
        removeHouse(this.house);
    }

    public void sleep(){
        this.energy++;
    }

}
