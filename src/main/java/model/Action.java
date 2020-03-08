package model;

import model.buildings.Building;

public class Action {
    private boolean isDone;
    private Building building;
    private String title;
    private Person worker;


    public Action(Building building, String title, Person worker) {
        isDone = false;
        this.building = building;
        this.title = title;
        this.worker = worker;
    }

    public boolean isDone(){
        return isDone;
    }

    public void performAction(){
        this.building.use(worker);
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
