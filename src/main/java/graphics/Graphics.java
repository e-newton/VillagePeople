package graphics;

import java.util.ArrayList;

public class Graphics {
    public static  ArrayList<Animatible> animatibles;
    public static  ArrayList<Drawable> drawables;

    public Graphics(){
        drawables = new ArrayList<>();
        animatibles = new ArrayList<>();
    }

    public void render(){
        animate();
        for (Drawable d: drawables) {
            d.draw();
        }

    }

    private void animate(){
        for(Animatible a : animatibles){
            a.animate();
        }
    }

    public static void addAnimatible(Animatible a) {animatibles.add(a);}

    public static void addDrawable(Drawable d){
        drawables.add(d);
    }

    public static void removeDrawable(Drawable d) { drawables.remove(d);}



}
