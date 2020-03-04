package main;

import engine.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window(512,512,"Village People");
        window.create();

        while(!window.closed()) {
            window.update();
            window.swapBuffers();
        }

    }


}
