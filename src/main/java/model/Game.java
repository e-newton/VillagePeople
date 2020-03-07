package model;

import engine.Input;
import engine.Timer;
import engine.Window;
import graphics.*;
import graphics.Graphics;
import math.Vector;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.sql.SQLOutput;

public class Game {
    private static Graphics graphics;
    private Window window;
    private Grid grid;
    private GridSquare selected;
    private DynamicQuadDrawing dynamicQuadDrawing;
    private GridMovingObject gridMovingObject;
    public Game(Window window, Graphics graphics) {
        this.graphics = graphics;
        this.window = window;
        Vector tl = new Vector(0, Window.getHeight());
        Vector br = new Vector(Window.getWidth(),0);
        this.grid = new Grid(tl,br,20,20);
        this.gridMovingObject = new GridMovingObject(this.grid.getGridSquareFromScreen(0,0),Color.GREEN);
        dynamicQuadDrawing = new DynamicQuadDrawing(
                new Vector(50,50), new Vector(60,50),
                new Vector(60,60), new Vector(50,60),
                Color.magenta
        );
        dynamicQuadDrawing.createBorder(Color.black, 0f);
        addDrawable(dynamicQuadDrawing);
        addAnimatible(dynamicQuadDrawing);




        //this.grid.rotateAroundPoint(Window.getCentre(), 45f);
    }

    public void run(){

        if(selected != null){
            if(Input.isKeyDown(GLFW.GLFW_KEY_1)){
                selected.setColour(Color.BLUE);
            }
            if(Input.isKeyDown(GLFW.GLFW_KEY_2)){
                selected.setColour(Color.GREEN);
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
                this.gridMovingObject.moveTo(selected);
            }
        }

        this.gridMovingObject.move();

    }
    public static void addAnimatible(Animatible a){graphics.addAnimatible(a);}

    public static void addDrawable(Drawable d){
        graphics.addDrawable(d);
    }

    public static void removeDrawable(Drawable d) { graphics.removeDrawable(d);}



}
