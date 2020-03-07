package model;

import engine.Input;
import engine.Timer;
import engine.Window;
import graphics.*;
import graphics.Graphics;
import math.Vector;
import model.buildings.Building;
import model.buildings.Farm;
import model.buildings.Granary;
import model.buildings.House;
import model.resources.FoodTypes;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Game {
    private static Graphics graphics;
    private Grid grid;
    private GridSquare selected;
    private DynamicQuadDrawing dynamicQuadDrawing;
    private GridMovingObject gridMovingObject;
    private ArrayList<Person> people;
    private double personTimer = 0;
    public Game(Window window, Graphics graphics) {

        this.graphics = graphics;
        Vector tl = new Vector(0, Window.getHeight());
        Vector br = new Vector(Window.getWidth(),0);
        this.grid = new Grid(tl,br,20,20);
        people = new ArrayList<>();

//        this.gridMovingObject = new GridMovingObject(this.grid.getGridSquareFromScreen(0,0),Color.GREEN);
//        dynamicQuadDrawing = new DynamicQuadDrawing(
//                new Vector(50,50), new Vector(60,50),
//                new Vector(60,60), new Vector(50,60),
//                Color.magenta
//        );
//        dynamicQuadDrawing.createBorder(Color.black, 0f);
//        addDrawable(dynamicQuadDrawing,2);
//        addAnimatible(dynamicQuadDrawing);




        //this.grid.rotateAroundPoint(Window.getCentre(), 45f);
    }


    public void run(){

        personTimer += Timer.dt;
        ArrayList<Person> deadPeople = new ArrayList<>();
        for(Person p : people){
            p.step();
            if(!p.isAlive()){
                p.die();
                deadPeople.add(p);
            }
        }

        people.removeAll(deadPeople);

        if(selected != null && personTimer > 1000){

            if(Input.isKeyDown(GLFW.GLFW_KEY_1)){
                System.out.println("AH");
                new Farm(selected, FoodTypes.APPLE);
                personTimer = 0;
            }
            if(Input.isKeyDown(GLFW.GLFW_KEY_2)){
                new Granary(selected);
                personTimer = 0;
            }
            if(Input.isKeyDown(GLFW.GLFW_KEY_3)){
                new House(selected);
                personTimer = 0;
            }
            if(Input.isKeyDown(GLFW.GLFW_KEY_SPACE)){
                Person p = new Person("Bob", selected, Color.blue);
                selected.addPerson(p);
                people.add(p);
                personTimer = 0;

            }
        }

        if(Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            Vector test = new Vector((float)Input.getMouseX(),(float)Input.getMouseY());
            //dynamicQuadDrawing.setDestination(test);
            if(grid.isOnGrid((float)Input.getMouseX(),(float)Input.getMouseY())){
                if(selected != null){
                    selected.setBorderColour(Color.BLACK);
                }
                selected = grid.getGridSquareFromScreen((float)Input.getMouseX(),(float)Input.getMouseY());
                selected.setBorderColour(Color.RED);
            }
        }


    }



    public static void addAnimatible(Animatible a){graphics.addAnimatible(a);}

    public static void addDrawable(Drawable d, int layer){
        graphics.addDrawable(d, layer);
    }

    public static void removeDrawable(Drawable d, int layer) { graphics.removeDrawable(d,layer);}



}
