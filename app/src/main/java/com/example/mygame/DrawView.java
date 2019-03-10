package com.example.mygame;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    int a = 0;
    final SurfaceHolder surfaceHolder = getHolder();
    private volatile boolean running = true;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    Character player = new Character(BitmapFactory.decodeResource(getResources(), R.drawable.character1)
            , 100, 150, 50, 150, 7, 6);
    Enemy dino1 = new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.dino)
            , 300, 500, 76, 58, 7, 5);

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread() {
            public void run() {
                while (running) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        try {
                            canvas.drawColor(Color.WHITE);
                            if (a >= 1) player.moveto();
                            player.update(System.currentTimeMillis());
                            player.draw(canvas);
                            dino1.updatee(System.currentTimeMillis());
                            dino1.drawe(canvas);
                            attackEnemy();

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

    public  boolean attackEnemy() {

        if (player.x + player.spriteWidth/4 <= dino1.x + dino1.spriteWidth && player.x+ 3*player.spriteWidth/4>= dino1.x && player.y+ player.spriteHeight/2 <= dino1.y + dino1.spriteHeight && player.y+player.spriteHeight/2 >= dino1.y) {
            player.stop();
            if (player.x > dino1.x) {
                player.a = 6;
                dino1.mod = 3;
            } else {
                player.a = 5; dino1.mod = 2;
            } return true;
        } else {player.a = 0; return false;}
    }
}