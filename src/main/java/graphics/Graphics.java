package graphics;

import java.util.ArrayList;

public class Graphics {

    public static  ArrayList<Drawable> drawables;

    public Graphics(){
        drawables = new ArrayList<>();
    }

    public void render(){
        for (Drawable d: drawables) {
            d.draw();
        }
    }

    public static void addDrawable(Drawable d){
        drawables.add(d);
    }

    public static void removeDrawable(Drawable d) { drawables.remove(d);}



}
