package model;

import graphics.QuadDrawing;
import math.Vector;

import java.awt.*;

public class GridSquare {
    private Color colour;
    private QuadDrawing quadDrawing;
    private int row;
    private int col;
    private float x;
    private float y;

    public GridSquare(int row, int col, Color colour){
        this.row = row;
        this.col = col;
        this.x = row * Grid.GRID_SQUARE_WIDTH;
        this.y = col * Grid.GRID_SQUARE_HEIGHT;
        this.colour = colour;
        this.quadDrawing = new QuadDrawing(new Vector(x,y), new Vector(x+Grid.GRID_SQUARE_WIDTH,y),
                new Vector(x+Grid.GRID_SQUARE_WIDTH,y + Grid.GRID_SQUARE_HEIGHT),
                new Vector(x,y + Grid.GRID_SQUARE_HEIGHT), colour);
        this.quadDrawing.createBorder(Color.BLACK, 10f);
        Game.addDrawable(this.quadDrawing);
    }

    public void rotateAroundPoint(Vector p, float angle){
        this.quadDrawing.rotateAroundPoint(p,angle);
    }

}
