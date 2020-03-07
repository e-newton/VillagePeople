package model;

import model.buildings.Building;
import model.buildings.Farm;
import model.buildings.Granary;
import model.buildings.House;
import model.resources.Food;
import model.resources.FoodTypes;

import java.awt.*;

public class Person extends GridMovingObject {
    public static final int MAX_INVENTORY = 5;
    public static final float RADIUS = 5f;
    private Farm farm;
    private Building destinationBuilding;
    private Food inventory;
    private House house;
    private int hunger = 100;
    private int energy = 1000;
    private boolean isSleeping = false;
    private String name;
    private GridMovingObject gridMovingObject;

    public Person(String name, GridSquare position, Color colour){
        super(position,colour, RADIUS);
        this.position.addPerson(this);
        this.name = name;
        this.destination = null;
        this.inventory = null;
        this.destination = null;
        this.house = null;
    }

    public Person(GridSquare position, Color colour){
        super(position, colour, RADIUS);
        this.position.addPerson(this);
        this.name = "Dick Billson";
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
            stop();
            house.removeOccupant(this);
        }
    }

    public void addFoodToInventory(Food f){
        this.inventory = f;
    }

    public void stop(){
        destination = null;
        destinationBuilding = null;
        path.clear();
    }

    public Food removeFoodFromInventory(){
        Food f = this.inventory;
        this.inventory = null;
        return f;
    }

    public boolean isAlive(){
        return energy >= 0;
    }

    public void step(){

        think();
        move();
        energy--;

    }

    public void think(){
        if(house == null){
            GridSquare gridWithBuilding = findClosestBuilding(new House());
            if(gridWithBuilding != null){
                House h = (House)  gridWithBuilding.getBuilding();
                h.addOccupant(this);
            }
        }

        if(isSleeping && energy >= 1000){
            isSleeping = false;
        }

        if(destination == position || isSleeping){
            position.getBuilding().use(this);
        }

        if(this.farm == null){
            GridSquare gridWithBuilding = findClosestBuilding(new Farm(FoodTypes.APPLE));
            if(gridWithBuilding != null){
                Farm farm = (Farm) gridWithBuilding.getBuilding();
                farm.setWorker(this);
            }
        }
        if(energy < 300 && house != null){
            destinationBuilding = house;
            moveTo(house.getGridSquare());
            return;

        }

        if(inventory == null && farm != null) {
            moveTo(farm.getGridSquare());

        } else if (inventory != null){
            GridSquare gridWithBuilding = findClosestBuilding(new Granary());
            if(gridWithBuilding != null){
                destinationBuilding = gridWithBuilding.getBuilding();
                moveTo(gridWithBuilding);
            }
        }

    }

    public void die(){
        stop();
        removeHouse(this.house);
        removeFarm();
        this.position.removePerson(this);
        kill();
    }

    public void sleep(){
        isSleeping = true;
        destination = house.getGridSquare();
        this.energy += 5;
    }

    public void setFarm(Farm farm) {
        if(this.farm == null){
            this.farm = farm;
            this.farm.setWorker(this);
        }
    }

    public void removeFarm() {
        if(this.farm != null){
            stop();
            Farm temp = this.farm;
            this.farm = null;
            temp.removeWorker();
        }
    }

    public void move(){
        if(destination == position){
            destination = null;
            return;
        }
        if(this.next == null){
            if(!path.isEmpty()) {
                this.next = path.get(0);
                path.remove(0);
                this.quad.setDestination(this.next.getCentre());
            } else if (destination != null){
                this.next = destination;
                this.quad.setDestination(this.next.getCentre());
            }

        }
        if(this.next != null &&this.quad.getCentre().equals(this.next.getCentre())){
            this.position.removePerson(this);
            this.position = this.next;
            this.position.addPerson(this);
            this.next = null;
        }


    }

    public void moveTo(GridSquare destination){
        if(!destination.equals(this.destination)){
            this.path.clear();
            this.destination = destination;
            search(destination);
            System.out.printf("");
        }

    }


}
