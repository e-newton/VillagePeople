package math;

import java.util.Objects;

//General Vector (2D for now)
public class Vector {
    public static final float DELTA = 0.000001f;
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




}
