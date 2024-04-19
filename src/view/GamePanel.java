package view;

import Controll.flappy_follow;
import Controll.flappy_hold;
import Controll.flappy_tap;
import model.Dragon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends GameScreen {
    private Boolean h=false;

    private BufferedImage dragonHoldImage,dragonTapImage,dragonFollowImage,arrowBackImage;
    private Dragon dragonHold,dragonTap, dragonFollow, arrowBack ;
    private Animation animationDragonHold,animationDragonTap, animationDragonFollow, animationArrowBack;
    private AFrameOnImage frameDragonFollow,frameDragonHold,frameArrowBack,frameDragonTap;

    public GamePanel(){

        super(750, 600);
        try {
            dragonTapImage=ImageIO.read((new File("image/dragon.png")));
            dragonFollowImage=ImageIO.read(new File("image/dragon3.png"));
            dragonHoldImage=ImageIO.read(new File("image/dragon2.png"));
            arrowBackImage=ImageIO.read(new File("image/arrow.jpg"));
        } catch (IOException e) {}

        animationDragonFollow=new Animation();
        animationDragonHold=new Animation();
        animationArrowBack=new Animation();
        animationDragonTap=new Animation();

        frameDragonTap = new AFrameOnImage(0,0,111,99);
        animationDragonTap.AddFrame(frameDragonTap);
        dragonTap=new Dragon(320,250,108,60);

        frameDragonFollow = new AFrameOnImage(0, 0, 111, 99);
        animationDragonFollow.AddFrame(frameDragonFollow);
        dragonFollow=new Dragon(100,250,111,99);

        frameDragonHold = new AFrameOnImage(0, 0, 111, 52);
        animationDragonHold.AddFrame(frameDragonHold);
        dragonHold=new Dragon(539,250,111,52);

        frameArrowBack = new AFrameOnImage(0, 0, 100, 83);
        animationArrowBack.AddFrame(frameArrowBack);
        arrowBack=new Dragon(5,5,100,83);
        BeginGame();

    }


    @Override
    public void GAME_UPDATE() {

    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {

        animationDragonTap.PaintAnims((int) dragonTap.getPosX(), (int) dragonTap.getPosY(), dragonTapImage, g2);
        animationDragonHold.PaintAnims((int) dragonHold.getPosX(),(int) dragonHold.getPosY(),dragonHoldImage,g2);
        animationDragonFollow.PaintAnims((int) dragonFollow.getPosX(),(int) dragonFollow.getPosY(),dragonFollowImage,g2);
        animationArrowBack.PaintAnims((int) arrowBack.getPosX(),(int) arrowBack.getPosY(),arrowBackImage,g2);

    }

    @Override
    public void MOUSE_ACTION(MouseEvent e, int Event) {
        if (h) {
            int x = e.getX(), y = e.getY();
            if (x <= (CUSTOM_WIDTH / 7) && y < (CUSTOM_WIDTH / 6)) {
                setVisible(false);
                if (stt == 0) {
                    new flappy_tap();
                } else if (stt == 1) {
                    new flappy_hold();
                } else {
                    new flappy_follow();
                }
            } else if (x <= (CUSTOM_WIDTH / 3) && y>(CUSTOM_HEIGHT*2/4) &&y<(CUSTOM_HEIGHT*3/4)) {

                setVisible(false);
                new flappy_follow();

            } else if (x > (CUSTOM_WIDTH / 3) && x < (CUSTOM_WIDTH * 2) / 3 && y>(CUSTOM_HEIGHT*2/4) &&y<(CUSTOM_HEIGHT*3/4)) {

                setVisible(false);
                new flappy_tap();

            } else if (x > (CUSTOM_WIDTH * 2) / 3 && y>(CUSTOM_HEIGHT*2/4) &&y<(CUSTOM_HEIGHT*3/4)) {

                setVisible(false);
                new flappy_hold();

            }
        }
        h=true;
    }

}



