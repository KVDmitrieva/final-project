package com.example.mygame;

import android.graphics.Bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Door {
    int idOfRoom;
    Bitmap image;
    int spriteWidth, spriteHeight;
    float x,y;
    Rect sourceRect;
    Door(int idOfRoom, Bitmap image, int count, int lines,float x, float y ){
        this.x = x;
        this.y = y;
        this.idOfRoom = idOfRoom;
        this.image = image;
        spriteWidth = image.getWidth() / count;
        spriteHeight = image.getHeight() / lines;
        sourceRect = new Rect(0,0, spriteWidth, spriteHeight);

    }
    void drawObject(Canvas canvas){
        this.sourceRect.left= idOfRoom* spriteWidth;
        this.sourceRect.right= this.sourceRect.left+ spriteWidth;
        this.sourceRect.bottom = spriteHeight;
        this.sourceRect.top = 0;
        Rect destRect=new Rect((int)x, (int)y, (int)x+ spriteWidth, (int)y+ spriteHeight);
        canvas.drawBitmap(image, sourceRect, destRect,null);
    }


}

