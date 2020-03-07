package model.buildings;

import model.Person;
import model.resources.Food;
import model.resources.FoodTypes;

public class Farm extends Building{
    private Person worker;
    private int foodProduced = 3;
    FoodTypes type;


    public Farm(FoodTypes type) {
        this.type = type;
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
    public void use(Person p) {
        p.addFoodToInventory(produceFood());
    }
}
