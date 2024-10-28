package Main;
import javax.swing.*;


public class main {

    public static final int WITDTH=540,HEIGHT=480;
    public static void main(String[] args) {
        JFrame theFrame=new JFrame("Brick Breaker");
        GamePanel thePanel=new GamePanel();
        Thread theThread=new Thread(thePanel);
        theFrame.setLocationRelativeTo(null);
        theFrame.setResizable(false);
        theFrame.setSize(WITDTH,HEIGHT);
        theFrame.add(thePanel);
        theThread.start();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }
}
