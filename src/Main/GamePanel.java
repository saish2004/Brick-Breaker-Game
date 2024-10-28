package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;
    private MyMouseMotionListener theMouseListener;

    private int mousex;
    //entities
    ball theball;
    paddle thepaddle;
    Map theMap;
    HUD theHUD;
    public GamePanel(){
        init();

    }
    public void init(){
        mousex=0;
        theball=new ball();
        thepaddle=new paddle();
        theMap=new Map(6,10);
        theHUD=new HUD();
        theMouseListener=new MyMouseMotionListener();
        addMouseMotionListener(theMouseListener);
        running=true;
        image=new BufferedImage(main.WITDTH,main.HEIGHT,BufferedImage.TYPE_INT_BGR);
        g=(Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    }
    @Override
    public void run() {
        while (running){
            //update
           update();
            //render or
            draw();
            //display
             repaint();
             try{
                 Thread.sleep(10);

             }catch( Exception e){
                 e.printStackTrace();
             }
        }

    }
    public void checkCollision(){
        Rectangle ballReact=theball.getRect();
        Rectangle paddleReact=thepaddle.getReact();

        if(ballReact.intersects(paddleReact)) {
            theball.setDy(-theball.getDy());

            if(theball.getX()<mousex+thepaddle.getWidth()/4){
                theball.setDx(theball.getDx()-.5);
            }
            if(theball.getX()<mousex+thepaddle.getWidth()&&theball.getX()>mousex+thepaddle.getWidth()/4){
                theball.setDx(theball.getDx()+.5);
            }
        }

      A:  for(int row=0;row<theMap.getMapArray().length;row++){
            for(int col=0;col<theMap.getMapArray()[0].length;col++){
                if(theMap.getMapArray()[row][col]>0){
                int brickx=col*theMap.getBrickWidth()+theMap.HDR_PAD;
                int bricky=row*theMap.getBrickHeight()+theMap.VERT_PAD;
                int brickWidth=theMap.getBrickWidth();
                int brickHeight=theMap.getBrickHeight();
                Rectangle brickRect=new Rectangle(brickx,bricky,brickWidth,brickHeight);
                if(ballReact.intersects(brickRect)){
                    theMap.hitBrick(row,col);
                    theball.setDy(-theball.getDy());
                    theHUD.addScore(50);
                    break A;
                }
            }
        }
        }

    }
    public void update(){
        checkCollision();
theball.update();

    }
    public void draw(){
        //background
        g.setColor(Color.WHITE);
        g.fillRect(0,0,main.WITDTH,main.HEIGHT);
        theMap.draw(g);
        theball.draw(g);
        thepaddle.draw(g);

        theHUD.draw(g);

        if(theMap.isThereAWin()==true){
            drawWin();
            running=false;
        }
        if(theball.youLose()){
            drawLoser();
            running=false;
        }

    }
    public void drawWin(){
        g.setColor(Color.RED);
        g.setFont(new Font( "Courier New",Font.BOLD,50));
        g.drawString("Winner!!",200,200);
    }
    public void drawLoser(){
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New",Font.BOLD,50));
        g.drawString("Loser!!",200,200);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D) g;


        g2.fillOval(20,20,20,60);
        g2.drawImage(image,0,0,main.WITDTH,main.HEIGHT,null);
        g2.dispose();
    }
private class MyMouseMotionListener implements MouseMotionListener{

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousex=e.getX();

        thepaddle.mouseMoved(e.getX());
    }
}
}
