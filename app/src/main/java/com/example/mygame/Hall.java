package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.List;

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


    boolean roomCreated = false;
    boolean moreWalls = false;

    int xl0, yl0, xr0, yr0, xl1, yl1, xr1, yr1,
            xl2, yl2, xr2, yr2, xl3, yl3, xr3, yr3;
    void createWalls(Hall r){
        xl0 = r.x;
        xr0 = r.x+r.width*size;
        yl0 = r.y-size/4;
        yr0 = r.y;

        xl1 = r.x+r.width*size;
        xr1 = r.x+r.width*size+size/4;
        yl1 = r.y;
        yr1 = r.y+r.height*size;

        xl2 = r.x;
        xr2 = r.x+r.width*size;
        yl2 = r.y+r.height*size;
        yr2 = r.y+r.height*size+size/4;

        xl3 = r.x-size/4;
        xr3 = r.x;
        yl3 = r.y;
        yr3 = r.y+r.height*size;
    }

    void addWall(List<Wall> walls){
        if(id%2==1){
            walls.add(new Wall(xl0, yl0, xr0, yr0));
            walls.add(new Wall(xl2, yl2, xr2, yr2));
        }
        else{
            walls.add(new Wall(xl3, yl3, xr3, yr3));
            walls.add(new Wall(xl1, yl1, xr1, yr1));
        }
    }

    void drawHall(Canvas canvas){

        Rect destRect=new Rect(x, y, x+width*size, y+height*size);
        canvas.drawBitmap(bitmap, sourceRect, destRect,null);
    }
}