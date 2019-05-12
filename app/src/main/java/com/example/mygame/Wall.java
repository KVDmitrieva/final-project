package com.example.mygame;

import android.graphics.Canvas;
import android.graphics.Paint;

class Wall {
     int x1,  y1, x2,  y2;
    Wall(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 =y2;
    }
    void drawWall(Canvas canvas, Paint p){
        canvas.drawRect(x1, y1, x2, y2, p);
    }
    void updateWallX(int velocity){
        x1+=velocity;
        x2+=velocity;
    }
    void updateWallY(int velocity){
        y1+=velocity;
        y2+=velocity;
    }
}
