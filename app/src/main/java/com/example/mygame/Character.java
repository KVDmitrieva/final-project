package com.example.mygame;

import android.graphics.Bitmap;

public class Character extends Sprite{
        int health =100, def = 5, attack = 5, crit = 7, speedAttack = 2;

    public Character(Bitmap bitmap, float x, float y, int width, int height, int fps, int frameCount, int lines) {
        super(bitmap, x, y, width, height, fps, frameCount, lines);
    }
    int maxhealth;

    public int getParam(String param){
        switch (param){
            case "maxheal": return maxhealth;
            case "curhealth": return health;
            case "def": return def;
            case "attack": return attack;
            case "crit": return crit;
            case "speed": return speedAttack;
            default: return health;
        }

    }



}
