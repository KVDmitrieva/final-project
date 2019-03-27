package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Sprite {
    private Bitmap bitmap;// Картинка с анимационной последовательностью
    private Rect sourceRect;// Прямоугольная область в bitmap, которую нужно нарисовать
    private int frameNr;// Число кадров в анимации
    int currentFrame;// Текущий кадр
    private long frameTicker;// время обновления последнего кадра
    private int framePeriod;// сколько миллисекунд должно пройти перед сменой кадра (1000/fps)

     int spriteWidth;// ширина спрайта (одного кадра)
     int spriteHeight;// высота спрайта

    float x;// X координата спрайта (верхний левый угол картинки)
    float y;// Y координата спрайта (верхний левый угол картинки)

    private float vX = 0;
    private float vY = 0;
    private float velocityX = 12;
    private float velocityY = 12;
    float corY, corX;
    public int mod; int a =0; int b = 0;

    public Sprite(Bitmap bitmap, float x, float y, int width, int height, int fps, int frameCount, int lines){
        this.bitmap= bitmap;
        this.x= x;
        this.y= y;

        currentFrame=0;
        frameNr= frameCount;
        spriteWidth= bitmap.getWidth()/ frameCount;
        spriteHeight= bitmap.getHeight()/lines;
        sourceRect=new Rect(0,0, spriteWidth, spriteHeight);
        framePeriod=1000/ fps;
        frameTicker= 0l;
        mod = b;


    }

    public void update(long gameTime){
        if(gameTime> frameTicker+ framePeriod){
            frameTicker = gameTime;
            currentFrame++;
            if(currentFrame>= frameNr){
                currentFrame=0;
            }
        }
// Определяем область на рисунке с раскадровкой, соответствующую текущему кадру
        if(mod == 0 ){
            this.sourceRect.left= currentFrame* spriteWidth;
            this.sourceRect.right= this.sourceRect.left+ spriteWidth;
            this.sourceRect.bottom = spriteHeight*(1+mod);
            this.sourceRect.top = spriteHeight*mod;} else
        if(mod == 1){
            this.sourceRect.left= currentFrame* spriteWidth ;
            this.sourceRect.right= this.sourceRect.left+ spriteWidth;
            this.sourceRect.bottom = spriteHeight*(1+mod);
            this.sourceRect.top = spriteHeight*mod;

        }else if(mod == 2){
            this.sourceRect.left= currentFrame* spriteWidth ;
            this.sourceRect.right= this.sourceRect.left+ spriteWidth;
            this.sourceRect.bottom = spriteHeight*(1+mod);
            this.sourceRect.top = spriteHeight*mod;
        }else if(mod == 3){
            this.sourceRect.left= currentFrame* spriteWidth ;
            this.sourceRect.right= this.sourceRect.left+ spriteWidth;
            this.sourceRect.bottom = spriteHeight*(1+mod);
            this.sourceRect.top = spriteHeight*mod;
        }else if(mod == 4){
            this.sourceRect.left= currentFrame* spriteWidth ;
            this.sourceRect.right= this.sourceRect.left+ spriteWidth;
            this.sourceRect.bottom = spriteHeight*(1+mod);
            this.sourceRect.top = spriteHeight*mod;
        }else if(mod == 5){
            this.sourceRect.left= currentFrame* spriteWidth ;
            this.sourceRect.right= this.sourceRect.left+ spriteWidth;
            this.sourceRect.bottom = spriteHeight*(1+mod);
            this.sourceRect.top = spriteHeight*mod;
        }else if(mod == 6){
            this.sourceRect.left= currentFrame* spriteWidth ;
            this.sourceRect.right= this.sourceRect.left+ spriteWidth;
            this.sourceRect.bottom = spriteHeight*(1+mod);
            this.sourceRect.top = spriteHeight*mod;
        }
    }

    void setVec(MotionEvent event){
        corX = event.getX();
        corY = event.getY();
        if(corX>=x) vX = 1; else vX = -1;
        if(corY>=y) vY = 1; else vY = -1;
        corX = corX +vX*(spriteWidth/2);
        corY = corY +vY*(spriteHeight/2);


    }
    void moveto(){
        if(corY>=y&&corY<=y+spriteHeight){
            if( corX>=x&&corX<=x+spriteWidth){
                mod = b;
            }else {  moveX(); mod = a;}
        } else { moveY(); mod = a;}

    }
    void moveX(){
        if(corX>x) a = 1; else a = 2;
        x += vX*velocityX;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void moveY(){
        if(corY>y) a = 3; else a = 4;
        y += vY*velocityY;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void stop(int v){
        b = v;
        mod = b;
        corY = y;
        corX = x;
    }


    public void draw(Canvas canvas){

        Rect destRect=new Rect((int)x, (int)y, (int)x+ spriteWidth, (int)y+ spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect,null);

    }
}
