package model;

import engine.Input;
import engine.Window;
import graphics.Graphics;
import graphics.QuadDrawing;
import math.Vector;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.sql.SQLOutput;

public class Game {
    private Graphics graphics;
    private Window window;
    public Game(Window window, Graphics graphics) {

        this.graphics = graphics;
        this.window = window;

    }


    public void run(){

        if(Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            System.out.println((float)Input.getMouseX() +"   " +(float)Input.getMouseY());
            System.out.println(window.screenToGraphics((float)Input.getMouseX(),(float)Input.getMouseY()).toString());
            Vector temp = window.screenToGraphics((float)Input.getMouseX(),(float)Input.getMouseY());
            graphics.addDrawable(new QuadDrawing(
                    new Vector(0.0f+temp.x,(25.0f/window.getHeight()+temp.y)),
                    new Vector((25.0f/window.getWidth()+temp.x),(25.0f/window.getHeight()+temp.y)),
                    new Vector((25.0f/window.getWidth()+temp.x),(0.0f+temp.y)),
                    new Vector((0.0f+temp.x),(0.0f+temp.y)),
                    Color.BLACK
            ));
        }

    }

}
