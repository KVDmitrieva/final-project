package com.example.mygame;

import android.graphics.Bitmap;
import android.view.MotionEvent;

    class Character extends Sprite{
       int health,  attack = 5; int crit = 7, speedAttack = 2, def = 5;

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

    void stop(int v){
        corY = y;
        corX = x;
        mod = v;
    }


}
