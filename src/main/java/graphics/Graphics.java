package graphics;

import java.util.ArrayList;
import java.util.Arrays;

public class Graphics {
    public static  ArrayList<Animatible> animatibles;
    public static ArrayList<ArrayList<Drawable>> layers;

    public Graphics(){
        layers = new ArrayList<>();
        animatibles = new ArrayList<>();
    }

    public void render(){
        animate();
        for(ArrayList<Drawable> layer: layers){
            for(Drawable d : layer){
                d.draw();
            }
        }

    }

    private void animate(){
        for(Animatible a : animatibles){
            a.animate();
        }
    }

    public static void addAnimatible(Animatible a) {animatibles.add(a);}

    public static void removeAnimatible(Animatible a){
        animatibles.remove(a);
    }

    public static void addDrawable(Drawable d, int layer){
        if(layers.size() <= layer){
            layers.add(new ArrayList<>(Arrays.asList(d)));
        }
        else{
            layers.get(layer).add(d);
        }
    }

    public static void removeDrawable(Drawable d, int layer) {
        layers.get(layer).remove(d);
    }



}
