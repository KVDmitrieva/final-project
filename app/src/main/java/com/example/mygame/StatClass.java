package com.example.mygame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;


public class StatClass {
     Bitmap bmHalf, dino1, dino2, floor;
     private Bitmap image, characterImage, character, dinoImage1,  dinoImage2, floorImage;
    int width, height, size;
    Map map;
    float coef ;
    Character player;
    Paint p;
    int m;
    int numberOfEnemy;
    List<Enemy> enemy;
    Door door;
    private DisplayMetrics displaymetrics;
    StatClass(Context context) {
        p = new Paint();
        m = 0;
        numberOfEnemy = 3;
        enemy = new ArrayList();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        displaymetrics = context.getResources().getDisplayMetrics();
       // width = displaymetrics.widthPixels;
       // height = displaymetrics.heightPixels;
        width = display.getWidth();
        height = display.getHeight();
        //coef = (float) (width * height) / (1700 * 900);
        coef = (float) (width * height) / (1500 * 2700);
        size = width/30;

        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.room);
        bmHalf = Bitmap.createScaledBitmap(image, width + 10, height - 100, false);


        characterImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.character);
        character = Bitmap.createScaledBitmap(characterImage, (int) (coef * characterImage.getWidth()), (int) (coef * characterImage.getHeight()), false);


        dinoImage1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.dino1);
        dino1 = Bitmap.createScaledBitmap(dinoImage1, (int) (coef * dinoImage1.getWidth()), (int) (coef * dinoImage1.getHeight()), false);

        dinoImage2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.dino2);
        dino2 = Bitmap.createScaledBitmap(dinoImage2, (int) (coef * dinoImage2.getWidth()), (int) (coef * dinoImage2.getHeight()), false);

        floorImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.floor);
        //floor = Bitmap.createScaledBitmap(dinoImage2, (int) (size * floorImage.getWidth()), (int) (size * floorImage.getHeight()), false);
        floor = Bitmap.createScaledBitmap(floorImage, (size * 12), (size * 12), false);


        player = new Character(character, 100, 150, 7, 6, 7, 1000);

        door = new Door(0, BitmapFactory.decodeResource(context.getResources(), R.drawable.door), 4, 1, (float)width / 2, (float)height / 2);

        map = new Map(width, height, floor, size);
    }
}