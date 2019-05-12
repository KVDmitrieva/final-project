package com.example.mygame;


import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;

class DrawView extends SurfaceView implements SurfaceHolder.Callback {

     public static int s;
     private DrawThread drawThread;



     DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getContext(),getHolder());
       drawThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
       drawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }
    int k, c;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        k = (int)event.getX();
        c = (int)event.getY();

        return super.onTouchEvent(event);
    }



}