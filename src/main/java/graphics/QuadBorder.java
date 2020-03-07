package graphics;

import math.Vector;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class QuadBorder extends QuadDrawing implements Drawable {

    float thickness;

    public QuadBorder(Vector topLeft, Vector topRight, Vector botRight, Vector botLeft, Color colour, float thickness) {
        super(topLeft, topRight, botRight, botLeft, colour);
        this.thickness = thickness;
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

    public void setBorderColour(Color colour) {
        this.colour = colour;
    }
}
