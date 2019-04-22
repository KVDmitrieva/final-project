package com.example.mygame;

import android.graphics.Bitmap;
import android.view.MotionEvent;

    class Character extends Sprite{
       int health,  attack = 5; //int crit = 7, speedAttack = 2, def = 5;

    private float vX = 0;
    private float vY = 0;
    private float velocity = 7;
    private float corY, corX;


    Character(Bitmap bitmap, float x, float y, int fps, int frameCount, int lines, int health) {
        super(bitmap, x, y,  fps, frameCount, lines);
        corX = x;
        corY = y;
        this.health = health;
    }
    void setVec(MotionEvent event){
        corX = event.getX();
        corY = event.getY();
        if(corX>=x) vX = 1; else vX = -1;
        if(corY>=y) vY = 1; else vY = -1;
        corX = corX +vX*((float)spriteWidth/2);
        corY = corY +vY*((float)spriteHeight/2);
    }

    void moveto(){
        if(corY>=y&&corY<=y+spriteHeight){
            if( corX>=x&&corX<=x+spriteWidth){
                // mod = b;
                stop(0);
            }else {  moveX(); mod = a;}
        } else { moveY(); mod = a;}

    }
    private void moveX(){
        if(corX>x) a = 1; else a = 2;
        x += vX*velocity;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void moveY(){
        if(corY>y) a = 3; else a = 4;
        y += vY*velocity;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void stop(int v){
        corY = y;
        corX = x;
        mod = v;
    }


}
