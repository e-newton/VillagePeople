package model;

import math.Vector;

import java.awt.*;

public class Grid {
    public static final float GRID_SQUARE_WIDTH= 25f;
    public static final float GRID_SQUARE_HEIGHT = 25;

    private int rows;
    private int cols;
    private GridSquare[][] squares;

    public Grid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.squares = new GridSquare[rows][cols];
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                this.squares[i][j] = new GridSquare(i,j, Color.WHITE);
            }
        }

    }

    public void rotateAroundPoint(Vector p, float angle){
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                this.squares[i][j].rotateAroundPoint(p,angle);
            }
        }
    }



}
