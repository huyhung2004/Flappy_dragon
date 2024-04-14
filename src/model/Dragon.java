package model;


import Controll.flappy_tap;
import view.Objects;
import view.SoundPlayer;

import java.awt.*;
import java.io.File;

public class Dragon extends Objects {
    public float vecto=0;
    private int isflying=2;
    private Rectangle rec;

    public SoundPlayer flap_sound,pong_sound,point_sound;
    public Dragon(float x, float y, float w, float h) {
        super(x, y, w, h);
        rec=new Rectangle((int) x, (int) y, (int) w, (int) h);
        flap_sound=new SoundPlayer(new File("image/fap.wav"));
        pong_sound=new SoundPlayer(new File("image/fall.wav"));
        point_sound=new SoundPlayer(new File("image/getpoint.wav"));

    }
    public void update_tap(){
        vecto+= flappy_tap.g;
        this.setPosY(this.getPosY()+vecto);
        this.rec.setLocation((int) this.getPosX(),(int) this.getPosY());
        if (vecto<0){
            isflying=1;
        }else isflying=0;

    }
    public void update_hold(){
        vecto+= flappy_tap.g;
        this.setPosY(this.getPosY()+vecto);
        this.rec.setLocation((int) this.getPosX(),(int) this.getPosY()+12);

    }
    public void update_follow(int x,int y){
        this.setPosY(y);
//        this.setPosX(x);
        this.rec.setLocation((int) this.getPosX(),(int) this.getPosY()+12);
        if (vecto<0){
            isflying=1;
        }else isflying=0;
    }

    public Rectangle getRec(){
        return rec;
    }
    public void setVecto(float vecto) {
        this.vecto = vecto;
    }

    public void fly(){
        vecto=-3.9f;
        flap_sound.play();
    }
    public void unfly(){
        vecto=0.3f;
    }

    public void fly2() {
        vecto=-2.4f;
    }
    public int getIsflying(){
        return isflying;

    }

    public void setIsflying(int isflying) {
        this.isflying = isflying;
    }
}
