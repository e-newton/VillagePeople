package graphics;

import java.util.ArrayList;

public class Graphics {

    ArrayList<Drawable> drawables;

    public Graphics(){
        drawables = new ArrayList<>();
    }

    public void render(){
        for (Drawable d: drawables) {
            d.draw();
        }
    }

    public void addDrawable(Drawable d){
        this.drawables.add(d);
    }



}
