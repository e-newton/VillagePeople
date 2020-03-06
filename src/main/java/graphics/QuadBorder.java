package graphics;

import math.Vector;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class QuadBorder implements Drawable {
    private Vector topLeft;
    private Vector topRight;
    private Vector botRight;
    private Vector botLeft;
    private Color colour;
    private float thickness;

    public QuadBorder(Vector topLeft, Vector topRight, Vector botRight, Vector botLeft, Color colour) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.botRight = botRight;
        this.botLeft = botLeft;
        this.colour = colour;
        this.thickness = 8f;
    }

    public void move(Vector v){
        topLeft.add(v);
        topRight.add(v);
        botLeft.add(v);
        botRight.add(v);
    }

    public void scale(float s){
        topLeft.scale(s);
        topRight.scale(s);
        botLeft.scale(s);
        botRight.scale(s);
    }

    public void rotateAroundPoint(Vector p, float angle){
        topLeft.rotateAroundPoint(p,angle);
        topRight.rotateAroundPoint(p,angle);
        botLeft.rotateAroundPoint(p,angle);
        botRight.rotateAroundPoint(p,angle);
    }
    @Override
    public void draw() {

        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE_LOOP);
        GL11.glLineWidth(this.thickness);
        GL11.glColor3f((float)colour.getRed()/255,(float)colour.getGreen()/255,(float)colour.getBlue()/255);
        GL11.glVertex2fv(Vector.toNormalizedDeviceCoords(botLeft).getCoordsArray());
        GL11.glVertex2fv(Vector.toNormalizedDeviceCoords(botRight).getCoordsArray());
        GL11.glVertex2fv(Vector.toNormalizedDeviceCoords(topRight).getCoordsArray());
        GL11.glVertex2fv(Vector.toNormalizedDeviceCoords(topLeft).getCoordsArray());
        GL11.glEnd();

    }
}
