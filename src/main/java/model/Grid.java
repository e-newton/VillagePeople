package model;

import math.Vector;

import java.awt.*;
import java.util.ArrayList;

public class Grid {
    public static float GRID_SQUARE_WIDTH;
    public static float GRID_SQUARE_HEIGHT;
    private Vector topLeft;
    private Vector bottomRight;

    private int rows;
    private int cols;
    private GridSquare[][] squares;
    private float rotation = 0;

    public Grid(Vector topLeft, Vector bottomRight, int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        float width = bottomRight.x - topLeft.x;
        float height = topLeft.y - bottomRight.y;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        GRID_SQUARE_WIDTH = width /cols;
        GRID_SQUARE_HEIGHT = height /rows;
        this.squares = new GridSquare[rows][cols];
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                this.squares[i][j] = new GridSquare(i,j, Color.WHITE);
            }
        }
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                getNeighborsForSquare(rows, cols, i, j);
            }
        }

    }

    public GridSquare[][] getSquares() {
        return squares;
    }

    private void getNeighborsForSquare(int rows, int cols, int i, int j) {
        ArrayList<GridSquare> neighbors = new ArrayList<>();
        if(i != 0){
            neighbors.add(squares[i-1][j]);
        }
        if(j != 0 ){
            neighbors.add(squares[i][j-1]);
        }
        if(i != rows - 1){
            neighbors.add(squares[i+1][j]);
        }
        if(j != cols - 1){
            neighbors.add(squares[i][j+1]);
        }

        this.squares[i][j].setNeighbors(neighbors);
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
