package model.buildings;

import model.GridSquare;
import model.Person;
import model.resources.Food;
import model.resources.FoodTypes;

import java.awt.*;

public class Farm extends Building{
    private static final Color FARM_COLOUR = Color.GREEN;
    private Person worker;
    private int foodProduced = 3;
    FoodTypes type;



    public Farm(GridSquare gridSquare, FoodTypes type) {
        this.type = type;
        this.colour = FARM_COLOUR;
        this.gridSquare = gridSquare;
        gridSquare.setBuilding(this);
    }

    public Farm(FoodTypes type) {
        this.type = type;
        this.colour = FARM_COLOUR;
    }

    public int getFoodProduced() {
        return foodProduced;
    }

    public void setFoodProduced(int foodProduced) {
        this.foodProduced = foodProduced;
    }

    public Food produceFood(){
        return new Food(type,foodProduced);
    }

    public Person getWorker() {
        return worker;
    }

    public void setWorker(Person worker) {
        if(this.worker == null){
            this.worker = worker;
            this.worker.setFarm(this);
        }

    }

    public void removeWorker(){
        if(this.worker != null){
            Person temp = worker;
            this.worker = null;
            temp.removeFarm();
        }
    }

    @Override
    public boolean isAvailable() {
        return worker == null;
    }

    @Override
    public void releaseWorkers() {
        removeWorker();
    }

    @Override
    public void use(Person p) {
        p.addFoodToInventory(produceFood());
        p.setActionToDone();
    }
}
