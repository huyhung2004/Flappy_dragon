package model;

import view.Objects;

import java.awt.*;

public class Column extends Objects {
    private Rectangle rec;
    private Boolean isBehinddragon=false;
    public Column(float x, float y, float w, float h) {
        super(x, y, w, h);
        rec=new Rectangle((int) x, (int) y, (int) w, (int) h);
    }
    public void update(){
        setPosX(getPosX()-2f);
        this.rec.setLocation((int) this.getPosX(),(int) this.getPosY());
    }
    public Rectangle getRec(){
        return rec;
    }

    public Boolean getBehinddragon() {
        return isBehinddragon;
    }

    public void setBehinddragon(Boolean behinddragon) {
        isBehinddragon = behinddragon;
    }
}
