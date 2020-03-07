package graphics;

import engine.Timer;
import math.Vector;

import java.awt.*;



public class DynamicQuadDrawing extends QuadDrawing implements Animatible {
    private float speed = 0.1f;
    private Vector direction;
    private Vector destination;


    public DynamicQuadDrawing(Vector topLeft, Vector topRight, Vector botRight, Vector botLeft, Color colour) {
        super(topLeft, topRight, botRight, botLeft, colour);
        direction = new Vector(0,0);
    }

    public void setDestination(Vector v){
        this.destination = v;
        direction = new Vector(destination.x-getCentre().x, destination.y-getCentre().y);
        direction.normalize();
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Vector getDestination(){
        return this.destination;
    }





    @Override
    public void animate() {
        if(destination != null){
            if(new Vector(destination.x-getCentre().x, destination.y-getCentre().y).magnitude() < (speed * Timer.dt)){
                setPosition(destination);
                destination = null;
            } else if(getCentre().equals(destination)){
                destination = null;
            } else{
                move(Vector.scaled(direction, (float) (speed * Timer.dt)));
            }

        }

    }
}
