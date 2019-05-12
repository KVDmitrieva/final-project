package com.example.mygame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

class StatClass {

     int width, height;
     Map map;
     Character player;
     Bitmap gameover, door;

    // int[] sd1={7, 5, 4, 100, 2, 6, 10, 2};
       // int[] sd2={7, 5, 3, 150, 2, 10, 10, 2};

    StatClass(Context context) {
        int size; float coef;
        Bitmap characterImage, character, dinoImage1,  dinoImage2, floorImage, game, level;
        Bitmap  dino1Image, dino2Image, floor;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics;
        displaymetrics = context.getResources().getDisplayMetrics();
        width = displaymetrics.widthPixels;
        height = displaymetrics.heightPixels;

        //width = display.getWidth();
        //height = display.getHeight();
        //coef = (float) (width * height) / (1700 * 900);
        coef = 1.5f*((float) (width * height) / (1500 * 2700));
        size = width/5;

        characterImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.character);
        character = Bitmap.createScaledBitmap(characterImage, (int) (coef * characterImage.getWidth()), (int) (coef * characterImage.getHeight()), false);


        dinoImage1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.dino1);
        dino1Image = Bitmap.createScaledBitmap(dinoImage1, (int) (coef * dinoImage1.getWidth()), (int) (coef * dinoImage1.getHeight()), false);

        dinoImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.dino2);
        dino2Image = Bitmap.createScaledBitmap(dinoImage2, (int) (coef * dinoImage2.getWidth()), (int) (coef * dinoImage2.getHeight()), false);

        floorImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.floor1);
        floor = Bitmap.createScaledBitmap(floorImage, (size * 12), (size * 12), false);

        level= BitmapFactory.decodeResource(context.getResources(), R.drawable.level);
        door = Bitmap.createScaledBitmap(level, (size ), (size ), false);


        game = BitmapFactory.decodeResource(context.getResources(), R.drawable.gameover);
        if(width>height)
        gameover = Bitmap.createScaledBitmap(game, (width-width/5), (height/2), false);
        else
            gameover = Bitmap.createScaledBitmap(game, (width-width/20), (height/3), false);



        player = new Character(character, (float) (width/2), (float)(height/2), 7, 6, 7, 1000);


        map = new Map(width, height, floor, size, dino1Image, dino2Image);

    }
}