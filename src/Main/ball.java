package Main;

import java.awt.*;

public class ball {

    private double x,y,dx,dy;
    private int ballSize=30;

    public ball(){
        x=200;
        y=200;
        dx=1;
        dy=3;
    }
    public void update(){
        setPosition();

    }
    public void setPosition(){
        x+=dx;
        y+=dy;
        if(x<0){
            dx=-dx;
        }
        if(y<0){
            dy=-dy;
        }
        if(x>main.HEIGHT-ballSize){
            dx=-dx;
        }
        if(y>main.HEIGHT-ballSize){
            dy=-dy;
        }
    }
    public void draw(Graphics2D g){
       g.setColor(Color.darkGray);
       g.setStroke(new BasicStroke(4));
       g.drawOval((int)x,(int) y,ballSize,ballSize);
    }
    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,ballSize,ballSize);
    }
    public void setDy(double theDy){
         dy=theDy;
    }
    public double getDy(){return dy;}

    public void setDx(double theDx){ dx=theDx;}

    public double getDx(){return dx;}

    public double getX() {
        return x;
    }
    public boolean youLose(){
        boolean loser=false;
        if(y>main.HEIGHT-ballSize*2){
            loser=true;
        }
        return loser;
    }
}
