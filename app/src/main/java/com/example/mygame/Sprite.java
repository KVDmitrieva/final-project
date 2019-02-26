package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Sprite {
    private Bitmap bitmap;// Картинка с анимационной последовательностью
    private Rect sourceRect;// Прямоугольная область в bitmap, которую нужно нарисовать
    private int frameNr;// Число кадров в анимации
    private int currentFrame;// Текущий кадр
    private long frameTicker;// время обновления последнего кадра
    private int framePeriod;// сколько миллисекунд должно пройти перед сменой кадра (1000/fps)

    private int spriteWidth;// ширина спрайта (одного кадра)
    private int spriteHeight;// высота спрайта

    private float x;// X координата спрайта (верхний левый угол картинки)
    private float y;// Y координата спрайта (верхний левый угол картинки)

    private float vX = 0;
    private float vY = 0;
    private float velocityX = 9;
    private float velocityY = 9;
    float corY, corX;
    int mod = 0;

    public Sprite(Bitmap bitmap, float x, float y, int width, int height, int fps, int frameCount){
        this.bitmap= bitmap;
        this.x= x;
        this.y= y;

        currentFrame=0;
        frameNr= frameCount;
        spriteWidth= bitmap.getWidth()/ frameCount;
        spriteHeight= bitmap.getHeight()/5;
        sourceRect=new Rect(0,0, spriteWidth, spriteHeight);
        framePeriod=1000/ fps;
        frameTicker= 0l;
        mod =0;
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
        }
    }

    void setVec(MotionEvent event){
        corX = event.getX();
        corY = event.getY();
        if(corX>=x) vX = 1; else vX = -1;
        if(corY>=y) vY = 1; else vY = -1;


    }
    void moveto(){
        if(corX>=x&&corX<=x+spriteWidth){
            if( corY>=y&&corY<=y+spriteHeight){
                mod = 0;
            }else {  moveY(); }
        } else { moveX();}

    }
    void moveX(){
        if(corX>x) mod = 1; else mod = 2;
        x += vX*velocityX;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void moveY(){
        if(corY>y) mod = 3; else mod = 4;
        y += vY*velocityY;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void draw(Canvas canvas){

        Rect destRect=new Rect((int)x, (int)y, (int)x+ spriteWidth, (int)y+ spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect,null);

    }
}
