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
        this.grid = new Grid((int)(Window.getWidth()/Grid.GRID_SQUARE_WIDTH)+1,(int)(Window.getHeight()/Grid.GRID_SQUARE_HEIGHT)+1);
        this.grid.rotateAroundPoint(Window.getCentre(), 45f);


    }

    public void run(){

        if(Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            Vector test = new Vector((float)Input.getMouseX(),(float)Input.getMouseY());
            QuadDrawing q = new QuadDrawing(
                    new Vector((float)Input.getMouseX(),(float)Input.getMouseY()),
                    new Vector((float)Input.getMouseX() + 25f,(float)Input.getMouseY()),
                    new Vector((float)Input.getMouseX() + 25f,(float)Input.getMouseY() + 25f),
                    new Vector((float)Input.getMouseX(),(float)Input.getMouseY() + 25f),
                    Color.BLACK
            );
            //q.rotateAroundPoint(q.getCentre(), 45f);
            addDrawable(q);
        }

    }

    public static void addDrawable(Drawable d){
        graphics.addDrawable(d);
    }

}
