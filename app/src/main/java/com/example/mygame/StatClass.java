package com.example.mygame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

class StatClass {

     static int score = 0;/*
    static int dir = -1;
    static int a = 0;
    static  int intersect = 0;
    static boolean canceled = false;
    static int enemies = 0;
    static int dungeon = 0;

    static final Object playerLock = new Object();
    static final Object enemyLock = new Object();
    static final Object mapLock = new Object();

*/


    static final Object playerHealthLock = new Object();
    static final Object playerLock = new Object();
    static final Object mapLock = new Object();
    static final Object enemyLock = new Object();
    static final Object doorLock = new Object();
    static final Object statsLock = new Object();



     int width, height, size;
     Map map;
     Character player;
     Bitmap gameover, door, health, def, attack, some;
     Door doord;


    // int[] sd1={7, 5, 4, 100, 2, 6, 10, 2};
       // int[] sd2={7, 5, 3, 150, 2, 10, 10, 2};

    StatClass(Context context) {
      float coef;
        Bitmap characterImage, character, dinoImage1,  dinoImage2, floorImage, game, level;
        Bitmap  dino1Image, dino2Image, floor, d, a, h;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics;
        displaymetrics = context.getResources().getDisplayMetrics();
        width = displaymetrics.widthPixels;
        height = displaymetrics.heightPixels;

        //width = display.getWidth();
        //height = display.getHeight();
        //coef = (float) (width * height) / (1700 * 900);
        coef = 2.5f*((float) (width * height) / (900 * 2700));
        size = width/5;

        characterImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.character);
        character = Bitmap.createScaledBitmap(characterImage, (int) (3*(size)*6)/4, (int) 6*(7*size)/4, false);


        dinoImage1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.dino1);
        dino1Image = Bitmap.createScaledBitmap(dinoImage1, (int) (1.5f*size*5), (4*size), false);

        dinoImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.dino2);
        dino2Image = Bitmap.createScaledBitmap(dinoImage2, (int) (1.5f*size*5), (4*size), false);

        floorImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.floor1);
        floor = Bitmap.createScaledBitmap(floorImage, (size * 12), (size * 12), false);

        level= BitmapFactory.decodeResource(context.getResources(), R.drawable.level);
        //door = Bitmap.createScaledBitmap(level, (size ), (size ), false);
        //some = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.level), size, size, false);

        d= BitmapFactory.decodeResource(context.getResources(), R.drawable.def);
        def = Bitmap.createScaledBitmap(d, (size/2 ), (size/2 ), false);

        h= BitmapFactory.decodeResource(context.getResources(), R.drawable.health);
        health = Bitmap.createScaledBitmap(h, (size/2 ), (size/2 ), false);

        a= BitmapFactory.decodeResource(context.getResources(), R.drawable.atack);
        attack = Bitmap.createScaledBitmap(a, (size/2 ), (size/2 ), false);

        doord =  new Door(level, size);



        game = BitmapFactory.decodeResource(context.getResources(), R.drawable.gameover);
        if(width>height)
            gameover = Bitmap.createScaledBitmap(game, (width-width/5), (height/2), false);
        else
            gameover = Bitmap.createScaledBitmap(game, (width-width/20), (height/3), false);



        player = new Character(character, (float) (width/2), (float)(height/2), 7, 6, 7, 1000);


        map = new Map(width, height, floor, size, dino1Image, dino2Image);

    }
}