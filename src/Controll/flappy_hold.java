package Controll;

import model.Columns;
import model.Entity;
import view.AFrameOnImage;
import view.Animation;
import view.menu.GamePanel;
import view.GameScreen;
import view.menu.Setting;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import static view.Objects.allowSound;

public class flappy_hold extends GameScreen {
    private boolean mouseIsPressed = false;
    private long mousePressedTime = 0;
    private BufferedImage dragon_image;
    private Entity dragonn;
    private int point=0;
    private Animation dragon_animation;

    private Columns columns;
    private int begin_Screen = 0;
    private int gameover_Screen = 2;
    private int gameplay_Screen = 1;
    private int current_Screen = begin_Screen;
    private Thread hold;
    private int max;

    public flappy_hold() {
        super(750, 600);
        stt=1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("image/HighscoreHold.txt"));
            String maxStr = reader.readLine();
            if (maxStr != null) {
                max = Integer.parseInt(maxStr);
            }
            reader.close();

            dragon_image = ImageIO.read(new File("image/dragon2.png"));
        } catch (IOException e) {
        }
        dragon_animation=new Animation();
        AFrameOnImage f;
        f = new AFrameOnImage(0, 0, 111, 52);
        dragon_animation.AddFrame(f);

        menu();
        hold= new Thread(this::hold_time);

        hold.start();



        dragonn = new Entity(350, 250, 108, 30);
        columns = new Columns();
        BeginGame();
    }
    public void hold_time(){
        while (true) {
            if (mouseIsPressed && System.currentTimeMillis() - mousePressedTime >= 150) {
                AFrameOnImage  ff;
                ff=new AFrameOnImage(0,0,111,52);
                dragon_animation.AddFrame(ff);
                dragonn.fly2();


            }


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
        }
    }

    public void reSetgame() {
        dragonn.setPos(350, 250);
        dragonn.setVecto(0);
        point=0;
        columns.Resetcolumns();
    }

    @Override
    public void GAME_UPDATE() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("image/HighscoreHold.txt"));
            writer.write(String.valueOf(max));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (current_Screen == begin_Screen) {
            reSetgame();

        } else if (current_Screen == gameplay_Screen) {
            dragonn.update_hold();
            columns.update();
            if (dragonn.getPosY() + dragonn.getH() > 750) {
                current_Screen = gameover_Screen;
            }
            if (dragonn.getPosY() + dragonn.getH() < -12) {
                current_Screen = gameover_Screen;
            }
            for (int i = 0; i < Columns.size; i++) {
                if (dragonn.getRec().intersects(columns.getColumn(i).getRec())){
                    current_Screen=gameover_Screen;
                    if (current_Screen==gameover_Screen&&allowSound){
                        dragonn.pong_sound.play();
                    }
                }
            }
            for (int i = 0; i < Columns.size; i++) {
                if (dragonn.getPosX()>columns.getColumn(i).getPosX()&& !columns.getColumn(i).getBehinddragon()&&i%2==0){

                    point++;
                    if (max<=point) max=point;
                    if (allowSound){
                        dragonn.point_sound.play();
                    }
                    columns.getColumn(i).setBehinddragon(true);
                }
            }

        }

    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
//        g2.setColor(Color.decode("#b8daef"));
//        g2.fillRect(0,0,CUSTOM_WIDTH,CUSTOM_HEIGHT);
        columns.paint(g2);

//        g2.setColor(Color.BLACK); // Màu viền
//        g2.draw(dragonn.getRec());

        dragon_animation.PaintAnims((int) dragonn.getPosX(), (int) (dragonn.getPosY()), dragon_image, g2);

        if (current_Screen == begin_Screen) {
//            g2.setColor(Color.red);
//            g2.drawString("Press space to play game", 200, 300);
            iconDragon.PaintAnims((int) dragonh.getPosX(),(int) dragonh.getPosY(), dragonImage,g2);
            iconMap.PaintAnims((int) maph.getPosX(),(int) maph.getPosY(), mapImage,g2);
            animationTap.PaintAnims((int) howToPlay.getPosX(),(int) howToPlay.getPosY(), holdImage,g2);

        } else if (current_Screen == gameover_Screen) {
            g2.setColor(Color.red);
            g2.drawString("game over",200,300);

        }
        if (current_Screen==gameplay_Screen || current_Screen==gameover_Screen){
            g2.setColor(Color.red);
            g2.drawString("POINT:"+point,30,40);
            g2.drawString("MAX: "+max,30,60);
        }
    }

    @Override
    public void MOUSE_ACTION(MouseEvent e, int Event) {
        if (e.getY() > (CUSTOM_HEIGHT *11 )/ 12 && current_Screen==begin_Screen) {
            if (e.getX()>CUSTOM_WIDTH/2){
                dispose();
                new GamePanel();
            }else {
                dispose();
                new Setting();
            }

        } else {
            if (Event == java.awt.event.MouseEvent.MOUSE_PRESSED) {
                mousePressedTime = System.currentTimeMillis();
                mouseIsPressed = true;
                if (current_Screen == begin_Screen) {
                    current_Screen = gameplay_Screen;

                } else if (current_Screen == gameplay_Screen) {

                } else if (current_Screen == gameover_Screen) {
                    current_Screen = begin_Screen;

                }

            }
            if (Event == MouseEvent.MOUSE_RELEASED) {
                mouseIsPressed = false;

            }
        }
    }
}








