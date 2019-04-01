package com.example.mygame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


 class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    final SurfaceHolder surfaceHolder = getHolder();
    private volatile boolean running = true;

     Paint p;
     public static int s;
     int m = 0;
     int numberOfEnemy = 3;
     List<Enemy> enemy = new ArrayList();

     Enemy dino1  = new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.dino1),
             random(), random(),  7, 5, 4, 100, 2, 6, 10, 2);
     DrawView(Context context) {
        super(context);
        p = new Paint();
        getHolder().addCallback(this);
    }


    protected void addEnemy(int numberOfEnemy) {
         int a = 0;
        if (numberOfEnemy > 0) {
            for (int i = 0; i < numberOfEnemy; i++) {
                float x = random(), y = random();
                //enemy.add(dino1);
                if(a==0){
               enemy.add(new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.dino1),
                        x, y,  7, 5, 4, 100, 2, 6, 10, 2)); a++;}
                        else {
                            a--;
                    enemy.add(new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.dino2),
                            x, y,  7, 5, 3, 150, 2, 10, 10, 2));

                }

            }
        }
    }
    Character player = new Character(BitmapFactory.decodeResource(getResources(), R.drawable.character)
            , 100, 150,  7, 6, 7, 1000);


    //clothes
    /*Cloth shirt1 = new Cloth(BitmapFactory.decodeResource(getResources(), R.drawable.shirt1)
            , 100, 150, 50, 150, 7, 6, 1);
    Cloth shirt2 = new Cloth(BitmapFactory.decodeResource(getResources(), R.drawable.shirt2)
            , 100, 150, 50, 150, 7, 6, 1); */



    Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.room);
    DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
    int width = displaymetrics.widthPixels;
    int height = displaymetrics.heightPixels;
    Bitmap bmHalf = Bitmap.createScaledBitmap(image, width+10, height-100, false);

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread() {
            public void run() {
                while (running) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        try {
                            canvas.drawBitmap(bmHalf, 0, -5,null);
                            if(s<2) { enemy.clear(); m=0;}else
                            if(s>1){

                                if(m==0) enemy.clear();
                                if(m<1){
                                    addEnemy(numberOfEnemy);m++;
                                }}
                            if (closeToEnemy()) attackEnemy(attacker); else
                            player.moveto();
                            player.update(System.currentTimeMillis());
                            onDrawEnemy(canvas);
                            player.draw(canvas);
                            drawHealth(canvas);
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
        float a = event.getX();
        int width = displaymetrics.widthPixels;
        if (width/7>a ) event.setLocation((float)width/7, event.getY()); else
            if(a>width*0.8) event.setLocation((float)4*width/5, event.getY());
        player.setVec(event);
        return super.onTouchEvent(event);
    }
    void attackEnemy(Enemy e){
         if (player.x > e.x) {
             player.stop(6);
             e.mod = 2;

         } else {
             player.stop(5);
             e.mod = 1;
         }
         if (e.currentFrame == 2){
             e.health = e.health - player.attack;}
         if(player.currentFrame ==3 )
             player.health = player.health - e.attack;

     }

    Enemy attacker;
     boolean closeToEnemy( ) {
        Iterator<Enemy> i = enemy.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (player.x + player.spriteWidth / 4 <= e.x + e.spriteWidth
                    && player.x + 3 * player.spriteWidth / 4 >= e.x
                    && player.y + player.spriteHeight / 2 <= e.y + e.spriteHeight
                    && player.y + player.spriteHeight / 2 >= e.y) {
                attacker = e;
                return true;
            }
        }return false;
    }


    private float random(){
    return (float)Math.random()*1000;
}

    public void onDrawEnemy(Canvas canvas) {
        Iterator<Enemy> i = enemy.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.health>0) {
                e.drawe(canvas);
                e.updates(System.currentTimeMillis());

            } else {
                i.remove();
                numberOfEnemy--;
            }
        }
    }
    void drawHealth(Canvas canvas){
         float xRect = (float)((player.health/10)*width/600);
        p.setColor(Color.WHITE);
        canvas.drawRect(20, 20, (float)width/6, (float)height/25, p);
        p.setColor(Color.RED);
         canvas.drawRect(20, 20, xRect, (float)height/25, p);

    }

}