package engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
    private int width, height;
    private String title;
    private long window;

    public Window(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {
        if(!GLFW.glfwInit()){
            System.err.println("Error, Window already exists");
            System.exit(-1);
        }
        window = GLFW.glfwCreateWindow(width,height,title,0,0);
        if(window == 0 ){
            System.err.println("Error creating window");
            System.exit(-1);
        }
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.width()-width)/2, (videoMode.height()-height)/2);
        GLFW.glfwShowWindow(window);

    }

    public boolean closed() {
        return GLFW.glfwWindowShouldClose(window);
    }
    public void update() {
        GLFW.glfwPollEvents();
    }
    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }
}
