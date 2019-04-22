package com.example.mygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import java.util.Iterator;
import java.util.List;

import static com.example.mygame.DrawView.s;

public class DrawThread extends Thread {
    StatClass stat;
    private SurfaceHolder surfaceHolder;

    private volatile boolean running = true;


    Paint p;
    int m;
    int numberOfEnemy;
    List<Enemy> enemy;
    Bitmap floor;
    int width , height;
    Character player;
    Door door;
    private Enemy attacker;
    Map map;
    int a =0; int size;



    public DrawThread(Context context, SurfaceHolder surfaceHolder) {

        this.surfaceHolder = surfaceHolder;
        stat= new StatClass(context);
        p = new Paint();
        player = stat.player;
        width = stat.width;
        height = stat.height;
        door = stat.door;
        m = stat.m;
        numberOfEnemy = stat.numberOfEnemy;
        enemy = stat.enemy;
        map = stat.map;
        floor = stat.floor;
        size = stat.size;

    }

     void requestStop() {
        running = false;
    }

   @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null) {
                try {

                    if (s!=1) {
                        canvas.drawBitmap(stat.bmHalf, 0, -5, null);
                        // door.drawObject(canvas);
                        if (s == 0) {
                            enemy.clear();
                            m = 0;
                        }
                        if (s == 2) {

                            if (m == 0) enemy.clear();
                            if (m < 1) {
                                addEnemy(numberOfEnemy);
                                m++;
                            }
                        }
                        if (closeToEnemy()) attackEnemy(attacker);
                        else
                            player.moveto();
                        player.update(System.currentTimeMillis());
                        onDrawEnemy(canvas);
                        player.draw(canvas);

                        drawHealth(canvas);
                    } else if (s == 1) {
                        canvas.drawColor(Color.BLACK);
                        if (a == 0) {
                            map.generate(15);
                            a++;
                        }
                        p.setColor(Color.YELLOW);
                        map.drawMap(canvas);
                    }


                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void addEnemy(int numberOfEnemy) {
        int a = 0;
        if (numberOfEnemy > 0) {
            for (int i = 0; i < numberOfEnemy; i++) {
                float x = (float)Math.random()*(float)Math.random()*width, y = (float)Math.random()*(float)Math.random()*height;
                if(width/7>x) x+=(float)width/7;
                else if (0.8*width<x) x-=2*(float)width/7;
                if(height/12>y) y+=(float)height/12;
                else if (11*height/12<x) x-=(float)height/12;
                //enemy.add(dino1);
                if(a==0){
                    enemy.add(new Enemy(stat.dino1, x, y,  7, 5, 4, 100, 2, 6, 10, 2)); a++;}
                else {
                    a--;
                    enemy.add(new Enemy(stat.dino2,x, y,  7, 5, 3, 150, 2, 10, 10, 2));
                }

            }
        }
    }

    private void attackEnemy(Enemy e){
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

    private boolean closeToEnemy( ) {
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

    private void onDrawEnemy(Canvas canvas) {
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

    private void drawHealth(Canvas canvas){
        float xRect = (float)((player.health/10)*width/600);
        p.setColor(Color.WHITE);
        canvas.drawRect(20, 20, (float)width/6, (float)height/25, p);
        p.setColor(Color.RED);
        canvas.drawRect(20, 20, xRect, (float)height/25, p);

    }

}
