package main;

import engine.Input;
import engine.Window;
import graphics.RectangleDrawing;
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
        if(Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            System.out.println(screenToGraphics((float)Input.getMouseX(),(float)Input.getMouseY()).toString());
            Vector temp = screenToGraphics((float)Input.getMouseX(),(float)Input.getMouseY());
            window.graphics.addDrawable(new RectangleDrawing(
                    new Vector(0.0f+temp.x,(10.0f/window.getHeight()+temp.y)),
                    new Vector((10.0f/window.getWidth()+temp.x),(10.0f/window.getHeight()+temp.y)),
                    new Vector((10.0f/window.getWidth()+temp.x),(0.0f+temp.y)),
                    new Vector((0.0f+temp.x),(0.0f+temp.y)),
                    Color.BLACK
            ));
        }



    }
    //Render Update
    public void render() {
        window.swapBuffers();
    }

    public Vector screenToGraphics(float screenX, float screenY){

        screenY /= window.getHeight();
        screenY *= 2;
        screenY = -screenY;
        screenY += 1.0f;

        screenX /= window.getWidth();
        screenX *= 2;
        screenX -= 1.0f;
        return new Vector(screenX,screenY);
    }



    public static void main(String[] args) {
        new Main().start();
    }



}
