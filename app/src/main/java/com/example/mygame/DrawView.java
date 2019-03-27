package com.example.mygame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    int a = 0;
    final SurfaceHolder surfaceHolder = getHolder();
    private volatile boolean running = true;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    List<Enemy> enemy = new ArrayList<Enemy>();
    protected void addEnemy() {
        for (int i = 0; i <3; i++) {
            float x = random(), y = random();

            enemy.add(new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.dino)
                    , x, y, 76, 58, 7, 5,
                    100, 2, 6, 10, 2));
        }
    }

    Character player = new Character(BitmapFactory.decodeResource(getResources(), R.drawable.character1)
            , 100, 150, 50, 150, 7, 6, 7);


    //clothes
    Cloth shirt1 = new Cloth(BitmapFactory.decodeResource(getResources(), R.drawable.shirt1)
            , 100, 150, 50, 150, 7, 6, 1);


    Cloth shirt2 = new Cloth(BitmapFactory.decodeResource(getResources(), R.drawable.shirt2)
            , 100, 150, 50, 150, 7, 6, 1);
    public static int s;
        int m = 0;
    Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.roomver);

    DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
    int width = displaymetrics.widthPixels;
    int height = displaymetrics.heightPixels;
    Bitmap bmHalf = Bitmap.createScaledBitmap(image, width, height, false);
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread() {
            public void run() {
                while (running) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        try {
                            if(s<2) {canvas.drawColor(Color.WHITE);deleteEnemy(); }else
                            if(s>1){
                               // canvas.drawColor(Color.BLUE);
                                canvas.drawBitmap(bmHalf, 0, 0,null);
                                if(m==0) deleteEnemy();
                                if(m<1){
                                    addEnemy();m++;
                                }}
                           // canvas.drawColor(Color.WHITE);
                            if (a >= 1) player.moveto();
                            player.update(System.currentTimeMillis());
                            player.draw(canvas);

                            attackEnemy();

                            onDrawEnemy(canvas);
                        } finally {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        player.setVec(event);
        a++;
        return super.onTouchEvent(event);
    }

    public void attackEnemy( ) {
        Iterator<Enemy> i = enemy.iterator();
        if (i.hasNext()==false) player.b = 0;
        while (i.hasNext()) {
            Enemy e = i.next();

            if (player.x + player.spriteWidth / 4 <= e.x + e.spriteWidth && player.x + 3 * player.spriteWidth / 4 >= e.x && player.y + player.spriteHeight / 2 <= e.y + e.spriteHeight && player.y + player.spriteHeight / 2 >= e.y) {
                if (player.x > e.x) {
                    player.mod=6;
                    player.stop(6);
                    //player.b = 6;
                    e.mod = 3;

                } else {
                    player.stop(5);
                   // player.mod = 5;
                    e.mod = 2;
                }
                if (e.currentFrame == 2){
                e.health = e.health - player.attack;}
                if(player.currentFrame ==3 )
                player.health = player.health - e.attack;

            } else {
                player.b = 0;
            }
        }
    }


public float random(){
        float x = (float)Math.random()*1000;
    return x;

}
    public void onDrawEnemy(Canvas canvas) {
        Iterator<Enemy> i = enemy.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.health>0) {
                e.drawe(canvas);
                e.updatee(System.currentTimeMillis());

            } else {
                i.remove();
            }
        }
    }
    public void deleteEnemy(){
        Iterator<Enemy> i = enemy.iterator();
        if (i.hasNext()==false) player.b = 0;
        while (i.hasNext()) {
            Enemy e = i.next();
            i.remove();
        }
    }


}