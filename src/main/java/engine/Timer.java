package engine;

public class Timer {

    public static double dt;
    public static double totalTime;
    private static double prevTime;


    public void update(){
        double tempTime = System.currentTimeMillis();
        dt = tempTime - prevTime;
        totalTime += dt;
        prevTime = tempTime;

    }


}
