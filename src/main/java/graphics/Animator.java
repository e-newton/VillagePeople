package graphics;

import java.util.ArrayList;

public class Animator {
    public static ArrayList<Animatible> animatibles = new ArrayList<>();
    private static double time;
    private static double dt;

    public void animate(){
        for (Animatible a : animatibles){
            a.animate();
        }
    }

    public void addAnimatible(Animatible a){animatibles.add(a);}

}
