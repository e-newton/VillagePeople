package main;

import engine.Input;
import engine.Window;
import graphics.QuadDrawing;
import math.Vector;
import org.lwjgl.glfw.GLFW;

import java.awt.*;


//The main class that starts our game and contains the game loop.
public class Main implements Runnable {
    public static final int WIDTH = 512;
    public static final int HEIGHT = 512;
    public Thread game;
    public static Window window;
    public static Color c = new Color(255, 108, 29);

    //Creates a new game thread
    public void start() {
        if(System.getProperty("os.name").equals("Mac OS X")){
            run();
        }
        else{
            game = new Thread(this, "game");
            game.start();
        }


    }

    //Initializes the window field for our game
    public static void init() {
        window = new Window(WIDTH, HEIGHT,"Village People");

        window.create();
        window.setBackgroundColour((float)c.getRed()/255, (float)c.getGreen()/255, (float)c.getBlue()/255);
        window.setFullScreen(false);


    }

    @Override
    // Run our game
    public void run() {
        init();
        //Game Loop
        while(!window.shouldClose()) {
            update();
            render();
            if(Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)){
                break;
            }
            if(Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullScreen(!window.isFullScreen());
        }

        window.destroy();

    }
    //Logic Update
    public void update(){
        window.update();
    }
    //Render Update
    public void render() {
        window.swapBuffers();
    }





    public static void main(String[] args) {
        new Main().start();
    }



}
