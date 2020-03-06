package graphics;

import math.Vector;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Random;

public class QuadDrawing implements Drawable{

    private Vector topLeft;
    private Vector topRight;
    private Vector botRight;
    private Vector botLeft;
    private Color colour;
    private QuadBorder border;

    public QuadDrawing(Vector topLeft, Vector topRight, Vector botRight, Vector botLeft, Color colour) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.botRight = botRight;
        this.botLeft = botLeft;
        this.colour = colour;
        createBorder(Color.CYAN, 0f);
    }

    public void createBorder(Color borderColour, float thinkness){

        border = new QuadBorder(topLeft,topRight,botRight,botLeft, borderColour);

    }

    public void rotateAroundPoint(Vector p, float angle){
        this.topLeft.rotateAroundPoint(p,angle);
        this.topRight.rotateAroundPoint(p,angle);
        this.botLeft.rotateAroundPoint(p,angle);
        this.botRight.rotateAroundPoint(p,angle);
//        if(border != null){
//            border.rotateAroundPoint(p,angle);
//        }
    }

    public void move(Vector v){
        topLeft.add(v);
        topRight.add(v);
        botLeft.add(v);
        botRight.add(v);
        if(border != null){
            border.move(v);
        }
    }

    public void scale(float s){
        topLeft.scale(s);
        topRight.scale(s);
        botLeft.scale(s);
        botRight.scale(s);
        if(border != null){
            border.scale(s);
        }
    }


    public Vector getCentre(){
        return new Vector((topLeft.x+botRight.x)/2, (topLeft.y+botRight.y)/2);
    }




    @Override
    public void draw() {

        GL11.glLineWidth(2);
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        GL11.glColor3f((float)colour.getRed()/255,(float)colour.getGreen()/255,(float)colour.getBlue()/255);
        GL11.glVertex2fv(Vector.toNormalizedDeviceCoords(botLeft).getCoordsArray());
        GL11.glVertex2fv(Vector.toNormalizedDeviceCoords(botRight).getCoordsArray());
        GL11.glVertex2fv(Vector.toNormalizedDeviceCoords(topRight).getCoordsArray());
        GL11.glVertex2fv(Vector.toNormalizedDeviceCoords(topLeft).getCoordsArray());
        GL11.glEnd();
        if(border != null){
            border.draw();
        }
//        scale(1.01f);
        // move(new Vector((new Random().nextFloat()-0.5f)/50f,(new Random().nextFloat()-0.5f)/50f));
    }
}
