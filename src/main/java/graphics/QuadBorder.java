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
        this.thickness = 5f;
    }
    @Override
    public void draw() {

        GL11.glBegin(GL11.GL_LINES);
        GL11.glLineWidth(this.thickness);
        GL11.glColor3f((float)colour.getRed()/255,(float)colour.getGreen()/255,(float)colour.getBlue()/255);
        GL11.glVertex2fv(botLeft.getCoordsArray());
        GL11.glVertex2fv(botRight.getCoordsArray());
        GL11.glVertex2fv(botRight.getCoordsArray());
        GL11.glVertex2fv(topRight.getCoordsArray());
        GL11.glVertex2fv(topRight.getCoordsArray());
        GL11.glVertex2fv(topLeft.getCoordsArray());
        GL11.glVertex2fv(topLeft.getCoordsArray());
        GL11.glVertex2fv(botLeft.getCoordsArray());
        GL11.glEnd();

    }
}
