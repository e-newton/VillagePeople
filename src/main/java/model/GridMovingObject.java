package model;

import graphics.DynamicQuadDrawing;
import graphics.Graphics;
import math.Vector;

import java.awt.*;
import java.util.*;

public class GridMovingObject {
    private GridSquare position;
    private ArrayList<GridSquare> path;
    private DynamicQuadDrawing quad;
    private GridSquare destination;
    private GridSquare next;
    private boolean isMoving = false;

    public GridMovingObject(GridSquare position, Color colour){
        this.position = position;
        this.quad = new DynamicQuadDrawing(
                new Vector(50,50), new Vector(60,50),
                new Vector(60,60), new Vector(50,60),
                colour);
        this.quad.setPosition(position.getCentre());
        this.quad.createBorder(Color.BLACK, 10f);
        Graphics.addDrawable(this.quad);
        Graphics.addAnimatible(this.quad);
        this.path = new ArrayList<GridSquare>();


    }

    public void move(){
        if(destination == position){
            destination = null;
            return;
        }
        if(this.next == null){
            if(!path.isEmpty()) {
                this.next = path.get(0);
                path.remove(0);
                this.quad.setDestination(this.next.getCentre());
            } else if (destination != null){
                this.next = destination;
                this.quad.setDestination(this.next.getCentre());
            }

        }
        if(this.next != null &&this.quad.getCentre().equals(this.next.getCentre())){
            this.position = this.next;
            this.next = null;
        }


    }

    public void moveTo(GridSquare destination){
        if(!destination.equals(this.destination)){
            this.path.clear();
            this.destination = destination;
            search(destination);
            System.out.printf("");
        }

    }

    public void search(GridSquare destination){
        ArrayList<GridSquare> finalPath = new ArrayList<>();
        ArrayList<GridSquare> openSet = new ArrayList<>();
        openSet.add(position);
        HashMap<GridSquare, GridSquare> cameFrom = new HashMap<GridSquare, GridSquare>();

        HashMap<GridSquare, Double> gScore = new  HashMap<GridSquare, Double>();
        gScore.put(position, 0.0);

        HashMap<GridSquare, Double> fScore = new  HashMap<GridSquare, Double>();
        fScore.put(position, getHScore(position, destination));

        while(!openSet.isEmpty()){
            GridSquare current = getLowestFScore(fScore,openSet);

            if(current.equals(destination)){
                //finalPath.add(current);
                while (cameFrom.containsKey(current)){
                    current = cameFrom.get(current);
                    finalPath.add(current);
                    //FINISHED HERE
                }
                Collections.reverse(finalPath);
                this.path = finalPath;

                return;
            }

            openSet.remove(current);
            for(GridSquare n : current.getNeighbors()){
                double tempGScore = gScore.get(current);
                if(tempGScore < gScore.getOrDefault(n, Double.MAX_VALUE)){
                    cameFrom.put(n, current);
                    gScore.put(n, tempGScore);
                    fScore.put(n, gScore.get(n) + getHScore(n, destination));
                    if(!openSet.contains(n)){
                        openSet.add(n);
                    }
                }
            }

        }

    }

    private double getHScore(GridSquare source, GridSquare dest){
        return source.getCentre().getDistanceTo(dest.getCentre());
    }

    private GridSquare getLowestFScore(HashMap<GridSquare, Double> fScore, ArrayList<GridSquare> openSet){
        GridSquare rv = null;
        double temp = Double.MAX_VALUE;
        for (GridSquare g : openSet){
            if(fScore.getOrDefault(g, Double.MAX_VALUE) < temp){
                temp = fScore.getOrDefault(g, Double.MAX_VALUE);
                rv = g;
            }
        }
        return rv;
    }





}
