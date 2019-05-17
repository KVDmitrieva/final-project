package com.example.mygame;

import android.graphics.Bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Door {

    private Bitmap image;
    int size;
    int x,y;
    private Rect sourceRect;
    Door( Bitmap image, int size ){
        //this.x = x;
        //this.y = y;

        this.image = image;
        this.size = size;
    }
    void drawObject(Canvas canvas){
        Rect destRect=new Rect(x, y, x+size, y+size);
        canvas.drawBitmap(image, sourceRect, destRect,null);
    }


}

