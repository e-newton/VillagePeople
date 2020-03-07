package model;

import graphics.DynamicQuadDrawing;
import graphics.Graphics;
import math.Vector;
import model.buildings.Building;
import model.buildings.Farm;
import model.resources.FoodTypes;

import java.awt.*;
import java.util.*;

public class GridMovingObject {
    protected GridSquare position;
    protected ArrayList<GridSquare> path;
    protected DynamicQuadDrawing quad;
    protected GridSquare destination;
    protected GridSquare next;

    public GridMovingObject(GridSquare position, Color colour, float radius){
        this.position = position;
        Vector centre = position.getCentre();
        this.quad = new DynamicQuadDrawing(
                new Vector(centre.x-radius,centre.y-radius), new Vector(centre.x+radius,centre.y-radius),
                new Vector(centre.x+radius,centre.y+radius), new Vector(centre.x-radius,centre.y+radius),
                colour);
        this.quad.setPosition(position.getCentre());
        this.quad.createBorder(Color.BLACK, 10f);
        Graphics.addDrawable(this.quad, 2);
        Graphics.addAnimatible(this.quad);
        this.path = new ArrayList<>();


    }

    public void kill(){
        this.path.clear();
        this.destination = null;
        Graphics.removeDrawable(this.quad, 2);
        Graphics.removeAnimatible(this.quad);
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

    public GridSquare findClosestFarm(){
        ArrayList<GridSquare> blocks = new ArrayList<>();
        ArrayList<GridSquare> seen = new ArrayList<>();
        blocks.add(this.position);
        while(!blocks.isEmpty()){
            GridSquare current = blocks.get(0);
            seen.add(current);
            blocks.remove(0);
            if(current.getBuilding() != null && Farm.class.equals(current.getBuilding().getClass())){
                Farm f = (Farm) current.getBuilding();
                if(f.getWorker() == null){
                    return current;
                }

            }
            for(GridSquare n : current.getNeighbors()){
                if(!seen.contains(n) && !blocks.contains(n)){
                    blocks.add(n);
                }
            }
        }


        return null;

    }

    public GridSquare findClosestBuilding(Building b){
        ArrayList<GridSquare> blocks = new ArrayList<>();
        ArrayList<GridSquare> seen = new ArrayList<>();
        blocks.add(this.position);
        while(!blocks.isEmpty()){
            GridSquare current = blocks.get(0);
            seen.add(current);
            blocks.remove(0);
            if(current.getBuilding() != null && b.getClass().equals(current.getBuilding().getClass())){
                return current;
            }
            for(GridSquare n : current.getNeighbors()){
                if(!seen.contains(n) && !blocks.contains(n)){
                    blocks.add(n);
                }
            }
        }


        return null;
    }





}
