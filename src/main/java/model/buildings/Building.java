package model.buildings;

import model.GridSquare;
import model.Person;

import java.awt.*;

public abstract class Building {

    protected GridSquare gridSquare;

    public GridSquare getGridSquare() {
        return gridSquare;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    protected Color colour;

    public abstract void use(Person p);


}
