package model.buildings;

import model.GridSquare;
import model.Person;

import java.awt.*;
import java.util.ArrayList;

public class House extends Building{
    private static final Color HOUSE_COLOUR = Color.RED;
    private ArrayList<Person> occupants;
    private static final int MAX_OCCUPANCY = 5;

    public House(GridSquare gridSquare){
        occupants = new ArrayList<>();
        this.colour = HOUSE_COLOUR;
        this.gridSquare = gridSquare;
        gridSquare.setBuilding(this);
    }

    public House(){
        occupants = new ArrayList<>();
        this.colour = HOUSE_COLOUR;
    }

    public void addOccupant(Person p) {
        if(!occupants.contains(p)){
            occupants.add(p);
            p.addHouse(this);
        }
    }

    public void removeOccupant(Person p){
        if(occupants.contains(p)) {
            occupants.remove(p);
            p.removeHouse(this);
        }
    }


    @Override
    public boolean isAvailable() {
        return occupants.size() < MAX_OCCUPANCY;
    }

    @Override
    public void releaseWorkers() {
        removeOccupants();
    }

    private void removeOccupants() {
        for (Person p : occupants){
            removeOccupant(p);
        }
    }

    @Override
    public void use(Person p) {
        if(occupants.contains(p)){
            p.sleep();
            if(p.getEnergy() > Person.FULLY_RESTED_ENERGY_LEVEL){
                p.setActionToDone();
            }
        }
    }
}
