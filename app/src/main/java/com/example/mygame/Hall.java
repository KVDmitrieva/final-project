package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Hall {
    int x, y, width, height, id, size;
    Bitmap bitmap;
    Rect sourceRect;

    Hall(int x, int y, int width, int height, int size, Bitmap image){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.size = size;
        bitmap = image;
        sourceRect=new Rect(0,0, size*width, size*height);

    }

    void drawHall(Canvas canvas){
        Rect destRect=new Rect(x, y, x+width*size, y+height*size);
        canvas.drawBitmap(bitmap, sourceRect, destRect,null);
    }
}