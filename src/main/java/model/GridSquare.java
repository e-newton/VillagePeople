package model;

import graphics.QuadDrawing;
import math.Vector;
import model.buildings.Building;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class GridSquare {
    private Color colour;
    private QuadDrawing quadDrawing;
    private int row;
    private int col;
    private float x;
    private float y;
    private Building building;
    private ArrayList<Person> people;

    private ArrayList<GridSquare> neighbors;

    public ArrayList<GridSquare> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void addPerson(Person p) {
        if(!people.contains(p)){
            people.add(p);
        }
    }

    public void removePerson(Person p) {
        if(people.contains(p)){
            people.remove(p);
        }
    }

    public void setBuilding(Building b){
        this.building = b;
        this.quadDrawing.setColour(b.getColour());
        this.colour = b.getColour();
    }

    public Building getBuilding() {
        return building;
    }

    public void setNeighbors(ArrayList<GridSquare> neighbors) {
        this.neighbors = neighbors;
    }

    public Vector getCentre(){
        return quadDrawing.getCentre();
    }

    public GridSquare(int row, int col, Color colour){
        this.row = row;
        this.col = col;
        this.x = row * Grid.GRID_SQUARE_WIDTH;
        this.y = col * Grid.GRID_SQUARE_HEIGHT;
        this.colour = colour;
        createQuadDrawing(colour);
        Game.addDrawable(this.quadDrawing,1);
        neighbors = new ArrayList<>();
        people = new ArrayList<>();
    }

    public void rotateAroundPoint(Vector p, float angle){
        this.quadDrawing.rotateAroundPoint(p,angle);
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        Game.removeDrawable(this.quadDrawing,1);
        this.colour = colour;
        createQuadDrawing(colour);
        Game.addDrawable(this.quadDrawing,1);
    }

    public void setBorderColour(Color colour){
        this.quadDrawing.setBorderColour(colour);
    }

    public void createQuadDrawing(Color colour) {
        this.quadDrawing = new QuadDrawing(new Vector(x, y), new Vector(x + Grid.GRID_SQUARE_WIDTH, y),
                new Vector(x + Grid.GRID_SQUARE_WIDTH, y + Grid.GRID_SQUARE_HEIGHT),
                new Vector(x, y + Grid.GRID_SQUARE_HEIGHT), colour);
        this.quadDrawing.createBorder(Color.BLACK, 10f);
    }
}
