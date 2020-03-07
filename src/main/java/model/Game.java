package model;

import engine.Input;
import engine.Window;
import graphics.Drawable;
import graphics.Graphics;
import graphics.QuadDrawing;
import math.Vector;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.sql.SQLOutput;

public class Game {
    private static Graphics graphics;
    private Window window;
    private Grid grid;
    public Game(Window window, Graphics graphics) {
        this.graphics = graphics;
        this.window = window;
        Vector tl = new Vector(0, Window.getHeight());
        Vector br = new Vector(Window.getWidth(),0);
        this.grid = new Grid(tl,br,20,20);
        //this.grid.rotateAroundPoint(Window.getCentre(), 45f);
    }

    public void run(){

        if(Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            Vector test = new Vector((float)Input.getMouseX(),(float)Input.getMouseY());
            if(grid.isOnGrid((float)Input.getMouseX(),(float)Input.getMouseY())){
                grid.getGridSquareFromScreen((float)Input.getMouseX(),(float)Input.getMouseY()).setColour(Color.CYAN);
            }
//            QuadDrawing q = new QuadDrawing(
//                    new Vector((float)Input.getMouseX(),(float)Input.getMouseY()),
//                    new Vector((float)Input.getMouseX() + 25f,(float)Input.getMouseY()),
//                    new Vector((float)Input.getMouseX() + 25f,(float)Input.getMouseY() + 25f),
//                    new Vector((float)Input.getMouseX(),(float)Input.getMouseY() + 25f),
//                    Color.BLACK
//            );
//            //q.rotateAroundPoint(q.getCentre(), 45f);
//            addDrawable(q);
        }

    }

    public static void addDrawable(Drawable d){
        graphics.addDrawable(d);
    }

    public static void removeDrawable(Drawable d) { graphics.removeDrawable(d);}

}
