package engine;

import graphics.Graphics;
import graphics.RectangleDrawing;
import main.Main;
import math.Vector;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.awt.*;

//The physical window we see on our screens when we run our game
public class Window {
    private int width, height;
    private String title;
    private long window;
    public int frames;
    public static long time;
    public Input input;
    private float backgroundR, backgroundB, backgroundG;
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized;
    private boolean isFullScreen;
    private int[] windowPosX = new int[1], windowPosY = new int[1];
    public Graphics graphics;





    public Window(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
        this.graphics = new Graphics();
    }
    //Creation of our window
    public void create() {
        if(!GLFW.glfwInit()){
            System.err.println("Error, Window already exists");
            return;
        }
        input = new Input();
        window = GLFW.glfwCreateWindow(width,height,title, isFullScreen ? GLFW.glfwGetPrimaryMonitor() : 0,0);
        if(window == 0 ){
            System.err.println("Error creating window");
            return;
        }

        graphics.addDrawable(new RectangleDrawing(
                new Vector(0.0f/ width,10.0f/height),
                new Vector(10.0f/ width,10.0f/height),
                new Vector(10.0f/ width,0.0f/height),
                new Vector(0.0f/width,0.0f/height),
                Color.BLACK
        ));

        //Set up window parameters
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        windowPosX[0] = (videoMode.width()-width)/2;
        windowPosY[0] = (videoMode.height()-height)/2;

        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);

        //Set Input Callbacks
        GLFW.glfwSetKeyCallback(window,input.getKeyboardCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScroll());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);


        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(window);
        time = System.currentTimeMillis();

    }

    private void createCallbacks() {
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    //Update our window
    public void update() {
        if(isResized){
            GL11.glViewport(0,0, width, height);
            isResized = false;
        }

        GL11.glClearColor(backgroundR, backgroundB, backgroundG, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();

        //Frames per second
        frames++;
        if(System.currentTimeMillis() > time + 1000){
            time = System.currentTimeMillis();
            GLFW.glfwSetWindowTitle(window, title + "("+frames+")");
            frames = 0;
        }
    }
    public void swapBuffers() {
        render();
        GLFW.glfwSwapBuffers(window);
    }

    public void render() {
        this.graphics.render();
    }

    public void setBackgroundColour(float r, float b, float g) {
        backgroundR = r;
        backgroundG = g;
        backgroundB = b;
    }

    public void destroy(){
        input.destroy();
        GLFW.glfwSetWindowShouldClose(window,true);
        GLFW.glfwDestroyWindow(window);
    }

    public float getBackgroundR() {
        return backgroundR;
    }

    public void setBackgroundR(float backgroundR) {
        this.backgroundR = backgroundR;
    }

    public float getBackgroundB() {
        return backgroundB;
    }

    public void setBackgroundB(float backgroundB) {
        this.backgroundB = backgroundB;
    }

    public float getBackgroundG() {
        return backgroundG;
    }

    public void setBackgroundG(float backgroundG) {
        this.backgroundG = backgroundG;
    }

    public boolean isResized() {
        return isResized;
    }

    public void setResized(boolean resized) {
        isResized = resized;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
        isResized = true;
        if(isFullScreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0,0,width, height, 0);
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0],windowPosY[0],width, height, 0);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
