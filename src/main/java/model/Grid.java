package model;

import math.Vector;

import java.awt.*;

public class Grid {
    public static float GRID_SQUARE_WIDTH;
    public static float GRID_SQUARE_HEIGHT;
    private Vector topLeft;
    private Vector bottomRight;

    private float width;
    private float height;

    private int rows;
    private int cols;
    private GridSquare[][] squares;
    private float rotation = 0;

    public Grid(Vector topLeft, Vector bottomRight, int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.width = bottomRight.x - topLeft.x;
        this.height = topLeft.y - bottomRight.y;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        GRID_SQUARE_WIDTH = this.width/cols;
        GRID_SQUARE_HEIGHT = this.height/rows;
        this.squares = new GridSquare[rows][cols];
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                this.squares[i][j] = new GridSquare(i,j, Color.WHITE);
            }
        }

    }

    public boolean isOnGrid(float x, float y){
        return (topLeft.x <= x && x <= bottomRight.x) && (bottomRight.y <= y && y <= topLeft.y);
    }

    public GridSquare getGridSquareFromScreen(float x, float y){
        x = x - topLeft.x;
        y = y - bottomRight.y;
        return squares[(int)(x/(GRID_SQUARE_WIDTH))][(int)(y/(GRID_SQUARE_HEIGHT))];



    }

    public void rotateAroundPoint(Vector p, float angle){
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                this.squares[i][j].rotateAroundPoint(p,angle);
            }
        }
        this.rotation += angle;
    }



}
