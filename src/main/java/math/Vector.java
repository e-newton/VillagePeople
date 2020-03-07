package math;

import engine.Window;

import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

//General Vector (2D for now)
public class Vector {
    public static final float DELTA = 0.001f;
    public float x;
    public float y;

    public Vector(){
        this.x = 0;
        this.y = 0;
    }
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Vector(double x, double y) {
        this.x = (float)x;
        this.y = (float)y;
    }
    public static Vector add(Vector v1, Vector v2){
        return new Vector(v1.x+v2.x, v1.y+v2.y);
    }

    public void add(Vector vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    public void normalize() {
        float m = magnitude();
        x /= m;
        y /= m;
    }

    public float magnitude(){
        return (float)Math.sqrt(Math.pow(x,2)+Math.pow(y,2)) ;
    }

    public static float dot(Vector v1, Vector v2){
        return v1.x*v2.x + v1.y*v2.y;
    }

    public float dot(Vector vector) {
        return this.x*vector.x + this.y*vector.y;
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }
    public float[] getCoordsArray(){
        return new float[]{x, y};
    }

    public void scale(float s){
        x *= s;
        y *= s;
    }

    public static Vector scaled(Vector v, float s){
        return new Vector(v.x*s,v.y*s);

    }

    public float getDistanceTo(Vector v){
        return new Vector(v.x - x, v.y - y).magnitude();
    }

    public boolean isOrthogonal(Vector v) {
        return (0 - DELTA < dot(v) && dot(v) < 0 + DELTA);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Math.abs(this.x - vector.x) < DELTA &&
                Math.abs(this.y - vector.y) < DELTA;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString(){
        return "(" + x + ", "+ y+")";
    }

    public void rotateAroundPoint(Vector p, float angle){
        float a = (float) Math.toRadians(angle);
        float tempx = (float) (((x - p.x) * cos(a)) - ((p.y - y) * sin(a)) + p.x);
        float tempy = (float) (((p.y - y) * cos(a)) + ((x - p.x) * sin(a)) + p.y);
        x = tempx;
        y = tempy;
    }





    public static Vector toNormalizedDeviceCoords(Vector v){
        float x = 2.0f * (v.x + 0.5f) / Window.getWidth() - 1.0f;
        float y = 2.0f * (v.y + 0.5f) / Window.getHeight() - 1.0f;
        return new Vector(x,y);
    }




}
