package Main;

import java.awt.*;

public class paddle {

    private double x;
    private int width,height;
    public final int YPOS =main.HEIGHT-100;
    //constructor
    public paddle(){
        width=100;
        height=20;
        x=main.WITDTH/2-width/2;

    }
    //update
    public void update(){

    }
    //draw
    public void draw(Graphics2D g){
        g.setColor(Color.darkGray);
        g.fillRect((int)x,YPOS,width,height);
    }
public void mouseMoved(int mouseXPos){
        x=mouseXPos;
        if(x>main.WITDTH-width) {
            x=main.WITDTH-width;
        }

}
public Rectangle getReact(){
        return new Rectangle((int) x,YPOS,width,height);
}
public int getWidth(){return width;}

}
