package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Room {
    int x, y, width, height;
    boolean idOfHalls[] = new boolean[4];
    List<Hall> room_hall = new ArrayList();
    int numberOfHalls;
    boolean hallCreated = false;
    Bitmap bitmap;
    int size;
    Rect sourceRect;
    Room(int x, int y, int width, int height, int size, Bitmap image){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        for(boolean n: idOfHalls) n=true;
        bitmap = image;
        this.size = size;
        sourceRect=new Rect(0,0, size*width, size*height);
    }

    int numberOfHalls(){
        return (int)(Math.random()*100)%4;
    }

    void drawRoom(Canvas canvas){
            Rect destRect=new Rect(x, y, x+width*size, y+height*size);
           canvas.drawBitmap(bitmap, sourceRect, destRect,null);
    }

}
