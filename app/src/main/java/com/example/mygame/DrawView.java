package com.example.mygame;


import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         drawThread.a = 0;
        float a = event.getX();
        if (drawThread.width/7>a ) event.setLocation((float)drawThread.width/7, event.getY()); else
            if(a>drawThread.width*0.8) event.setLocation((float)4*drawThread.width/5, event.getY());
        drawThread.player.setVec(event);
        return super.onTouchEvent(event);
    }



}