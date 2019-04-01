package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

    class Sprite {
    private Bitmap bitmap;// Картинка с анимационной последовательностью
    private Rect sourceRect;// Прямоугольная область в bitmap, которую нужно нарисовать
    private int frameNr;// Число кадров в анимации
    int currentFrame;// Текущий кадр
    private long frameTicker;// время обновления последнего кадра
    private int framePeriod;// сколько миллисекунд должно пройти перед сменой кадра (1000/fps)

     int spriteWidth;
     int spriteHeight;

    float x;
    float y;

     int mod; int a =0;

     Sprite(Bitmap bitmap, float x, float y, int fps, int frameCount, int lines){
        this.bitmap= bitmap;
        this.x= x;
        this.y= y;

        currentFrame=0;
        frameNr= frameCount;
        spriteWidth= bitmap.getWidth()/ frameCount;
        spriteHeight= bitmap.getHeight()/lines;
        sourceRect=new Rect(0,0, spriteWidth, spriteHeight);
        framePeriod=1000/ fps;
        frameTicker= 01;
        mod = 0;

    }


    public void update(long gameTime){
        if(gameTime> frameTicker+ framePeriod){
            frameTicker = gameTime;
            currentFrame++;
            if(currentFrame>= frameNr){
                currentFrame=0;
            }
        }
        this.sourceRect.left= currentFrame* spriteWidth;
        this.sourceRect.right= this.sourceRect.left+ spriteWidth;
        this.sourceRect.bottom = spriteHeight*(1+mod);
        this.sourceRect.top = spriteHeight*mod;

    }

    public void draw(Canvas canvas){
        Rect destRect=new Rect((int)x, (int)y, (int)x+ spriteWidth, (int)y+ spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect,null);

    }
}
