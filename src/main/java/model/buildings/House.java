package model.buildings;

import model.Person;

import java.util.ArrayList;

public class House extends Building{
    private ArrayList<Person> occupants;
    private static final int MAX_OCCUPANCY = 5;

    public House(){
        occupants = new ArrayList<>();
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
    public void use(Person p) {
        if(occupants.contains(p)){
            p.sleep();
        }
    }
}
