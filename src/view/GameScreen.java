package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public abstract class GameScreen extends JFrame implements java.awt.event.MouseListener, java.awt.event.MouseMotionListener {

    public int x_mouse ;
    public int y_mouse;
    public int CUSTOM_WIDTH  = 500;
    public int CUSTOM_HEIGHT = 500;

    private GameThread G_Thread;

    public static int MASTER_WIDTH = 500, MASTER_HEIGHT = 500;

    public GameScreen(){
        InitThread();
        InitScreen();
    }

    public void RegisterImage(int id, BufferedImage image){


    }

    public BufferedImage getImageWithID(int id){
        return null;
    }

    public GameScreen(int w, int h){
        Toolkit toolkit =this.getToolkit();
        Dimension dimension=toolkit.getScreenSize();

        this.setBounds((dimension.width-w)/2,(dimension.height-h)/2,w,h);

        this.CUSTOM_WIDTH = w;
        this.CUSTOM_HEIGHT = h;
        MASTER_WIDTH = CUSTOM_WIDTH;
        MASTER_HEIGHT = CUSTOM_HEIGHT;
        InitThread();
        InitScreen();
    }

    private void InitScreen(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setSize(CUSTOM_WIDTH, CUSTOM_HEIGHT);
        setVisible(true);

    }

    public void BeginGame(){
        G_Thread.StartThread();
    }

    private void InitThread(){
        G_Thread = new GameThread(this);
        add(G_Thread);
    }



    public abstract void GAME_UPDATE(long deltaTime);
    public abstract void GAME_PAINT(Graphics2D g2);


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        MOUSE_ACTION(e, MouseEvent.MOUSE_PRESSED);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MOUSE_ACTION(e, MouseEvent.MOUSE_RELEASED);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        x_mouse = e.getX();
        y_mouse = e.getY();

    }
    public abstract void MOUSE_ACTION(MouseEvent e, int Event);
}
