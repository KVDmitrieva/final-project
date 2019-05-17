package com.example.mygame;


import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class DrawView extends SurfaceView implements SurfaceHolder.Callback {

     private DrawThread d;
    Map map; Character player; Door door;
     DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
        StatClass stat = new StatClass(getContext());
        map = stat.map;
        player = stat.player;
        door = stat.doord;



    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        d = new DrawThread(getContext(), getHolder());

        d.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
       d.requestStop();
       // g.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                d.join();
               // gameThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }



}