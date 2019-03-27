package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Enemy  {
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
    int mod = 0;
    int health, def, attack, crit, speedAttack;
    public Enemy(Bitmap bitmap, float x, float y, int width, int height, int fps, int frameCount,int health, int def, int attack, int crit, int speedAttack){
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


        this.health = health;
        this.def = def;
        this.attack = attack;
        this.crit = crit;
        this.speedAttack = speedAttack;
    }
    int count = 0;
    public void updatee(long gameTime){
        if(gameTime> frameTicker+ framePeriod){
            frameTicker = gameTime;
            currentFrame++;
            if(currentFrame>= frameNr){
                currentFrame=0; count++;
                if(count==6){
                    mod =1; count = 0;
                } else if(count ==1 && mod ==1) mod = 0;
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
    int drawme =1;
    public void remove(){
         drawme = 0;
    }
    public void drawe(Canvas canvas){
        Rect destRect=new Rect((int)x, (int)y, (int)x+ spriteWidth, (int)y+ spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect,null);

    }
}
