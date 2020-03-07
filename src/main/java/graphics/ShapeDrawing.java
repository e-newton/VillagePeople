package graphics;

import math.Vector;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

public class ShapeDrawing implements Drawable{

    Collection<Vector> vertices;
    Color fillColour;

    public ShapeDrawing(Collection<Vector> vertices, Color colour){
        this.vertices = vertices;
        this.fillColour = colour;
    }


    @Override
    public void draw() {
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f((float)fillColour.getRed()/255,(float)fillColour.getGreen()/255,(float)fillColour.getBlue()/255);
        for(Vector v: vertices) {
            GL11.glVertex2fv(v.getCoordsArray());
        }
    }
}
