package com.example.mygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Iterator;

import static com.example.mygame.StatClass.score;

public class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;

    private volatile boolean running = true;


    static int dir = -1;
    static int a = 0;
    static  int intersect = 0;
    static boolean canceled = false;
    static int enemies = 0;
    static int dungeon = 0;

    private Paint p;
    private int width;
    private int height;
    private Character player;
    private Door door;
    private Enemy attacker;
    private Map map;
    private int velocityX , velocityY;
    private Bitmap gameover;
    private Bitmap level;
    private StatClass stat;

     DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
         stat= new StatClass(context);
        p = new Paint();
       player = stat.player;
        width = stat.width;
        height = stat.height;
        map = stat.map;
         //this.player = player;
         //this.map = map;
        gameover = stat.gameover;
        level = stat.door;
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
                    tick();
                    render(canvas);

                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
    private boolean health = false, attack = false, def = false;
     int fpsH = 0, fpsA = 0, fpsD = 0;
     int xStat=0, yStat=0;

    private void tick(){
        if (a == 0) {
            map.generate(5);
            createDoor();
            a++;
            dungeon++;
        }
         if(player.health>0){
        if(!intersectDoor()||canceled){
            intersect = 0;
            if(!intersectDoor())canceled =false;
            if(!closeToWall()&&!closeToEnemy()) checkDir(dir);
            else if(closeToEnemy())attackEnemy(attacker);

        }else{intersect = 1; player.mod=0;}}
    }

    private void render(Canvas canvas){
        canvas.drawColor(Color.BLACK);
         if(player.health>0){
        map.drawMap(canvas);
        door.drawObject(canvas);
        onDrawEnemy(canvas);
        player.draw(canvas);
        if(health){
            canvas.drawBitmap(stat.health, xStat, yStat,null);
            fpsH++;
            if(fpsH==5){
                health = false;
                fpsH = 0;
            }
        }
        if(attack){
            canvas.drawBitmap(stat.attack, xStat, yStat,null);
            fpsA++;
            if(fpsA==5){
                attack = false;
                fpsA = 0;
            }
        }
        if(def){
            canvas.drawBitmap(stat.def, xStat, yStat,null);
            fpsD++;
            if(fpsD==5){
                def = false;
                fpsD = 0;
            }
        }


        drawHealth(canvas);}
         else{canvas.drawColor(Color.BLACK);
             canvas.drawBitmap(gameover, (float)(width/2-gameover.getWidth()/2),(float)(height/2-gameover.getHeight()/2),null);
             String finalScore = "Your score is "+ String.valueOf(score);
             Paint p = new Paint();p.setColor(Color.WHITE);  p.setTextSize((float)(width/20));
             canvas.drawText(finalScore, (float)(width/2-gameover.getWidth()/4), (float)(height/2+2*gameover.getHeight()/3), p);

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
            player.health = player.health - (int)(e.attack*(1-0.01f*player.def));

    }

    private boolean closeToEnemy( ) {
        for(Room r: map.map_room){
        Iterator<Enemy> i = r.enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (player.x + player.spriteWidth / 4 <= e.x + e.spriteWidth
                    && player.x + 3 * player.spriteWidth / 4 >= e.x
                    && player.y + player.spriteHeight / 2 <= e.y + e.spriteHeight
                    && player.y + player.spriteHeight / 2 >= e.y) {
                attacker = e;
                return true;
            }
        }}return false;
    }

    private void onDrawEnemy(Canvas canvas) {
        for (Room r : map.map_room) {
            Iterator<Enemy> i = r.enemies.iterator();
            while (i.hasNext()) {
                Enemy e = i.next();
                if (e.health > 0) {
                    e.drawe(canvas);
                    e.updates(System.currentTimeMillis());

                } else {
                    destroyEnemy(e);
                    i.remove();
                    enemies++;
                }
            }
        }
    }

    void destroyEnemy(Enemy e){
         score +=e.id*100;
         xStat = (int)e.x;
         yStat = (int)e.y;

         int rand = (int)(Math.random()*100);
         if(rand>=10&&rand<=30){
             player.health+=100;
             health = true;
         } else if(rand>=40&&rand<=60){
             player.def+=1;
             def = true;
         } else if(rand>=70&&rand<=90){
             player.attack+=5;
             attack = true;
         }


    }

    private void drawHealth(Canvas canvas){
        float xRect = (float)(20+(player.health/10)*width/600);
        p.setColor(Color.WHITE);
        canvas.drawRect(20, 20, (float)width/6, (float)height/25, p);
        p.setColor(Color.RED);
        canvas.drawRect(20, 20, xRect, (float)height/25, p);

    }

    private void checkDir(int dir){
         int velocity = 7;
        if(dir==0){
            velocityX = velocity;
            player.mod = 2;
            moveRoomX();
        } else if(dir==2){
            velocityY = velocity;
            player.mod = 4;
            moveRoomY();
        } else if(dir==4){
            velocityX = -velocity;
            player.mod = 1;
            moveRoomX();
        } else if(dir==6){
            velocityY = -velocity;
            player.mod = 3;
            moveRoomY();
        } else player.mod = 0;
    }

    private boolean closeToWall(){
         boolean closeTo = false, oneMore = false;
         float x1 = player.x, x2 = player.x+player.spriteWidth,
                 y1 = player.y+(float)player.spriteHeight/4, y2 = player.y+player.spriteHeight;
         for(Wall w:map.map_walls) {
             if(dir==0){
                 if(x1-5>=w.x1&&x1-5<=w.x2&&(y1<=w.y2&&y1>=w.y1|| y2>=w.y1&&y2<=w.y2))
                        oneMore = true;
             } else if (dir==2){
                 if(y1-5>=w.y1&&y1-5<=w.y2&&(x1<=w.x2&&x1>=w.x1||x2<=w.x2&&x2>=w.x1))
                     oneMore = true;

             } else if(dir==4){
                 if(x2+5>=w.x1&&x2+5<=w.x2&&(y1<=w.y2&&y1>=w.y1|| y2>=w.y1&&y2<=w.y2))
                     oneMore = true;

             } else if(dir==6){
                 if(y2+5>=w.y1&&y2+5<=w.y2&&(x1<=w.x2&&x1>=w.x1||x2<=w.x2&&x2>=w.x1))
                     oneMore = true;

             }
             if(oneMore){
                 closeTo = true;
                 player.mod = 0;}
             }

         return closeTo;
    }

    private void moveRoomX(){
        for(Room r:map.map_room){
            r.x+=velocityX;
            r.moveEnemiesX(velocityX);
            for(Hall h:r.room_hall){
                h.x+=velocityX;
            }
        }
        for(Wall w:map.map_walls){
            w.updateWallX(velocityX);
        }
        door.x+=velocityX;

    }

    private void moveRoomY(){
        for(Room r:map.map_room){
            r.y += velocityY;
           r.moveEnemiesY(velocityY);
            for(Hall h:r.room_hall){
                h.y+= velocityY;
            }
        }
        for(Wall w:map.map_walls){
            w.updateWallY(velocityY);
        }
        door.y+=velocityY;
    }

    private void createDoor(){
         int idOfRoom = (int)(Math.random()*100)%map.map_room.size();
         Room r = map.map_room.get(idOfRoom);
         int x = r.x+((int)(Math.random()*100)%r.width)*r.size;
         int y = r.y+((int)(Math.random()*100)%r.height)*r.size;
         door = new Door(idOfRoom, level,x,y, r.size);
        Iterator<Enemy> i = r.enemies.iterator();
        while (i.hasNext()) {
            Enemy enemy = i.next();
            if(enemy.x>=x&&enemy.x<=x+r.size||enemy.x+enemy.spriteWidth>=x&&enemy.x+enemy.spriteWidth<=x+r.size){
                if(enemy.y>=y&&enemy.y<=y+r.size||enemy.y+enemy.spriteHeight>=y&&enemy.y+enemy.spriteHeight<=y+r.size){
                    i.remove();
                }
            }
        }
    }

    private boolean intersectDoor(){
         int px1 = (int)player.x;
         int px2 = (int)player.x+player.spriteWidth;
         int py1 = (int)player.y;
         int py2 = (int)player.y+player.spriteHeight;

         int dx1 = door.x;
         int dx2 = door.x+door.size;
         int dy1 = door.y;
         int dy2 = door.y+door.size;

         if(px1>dx1&&px1<dx2||px2>dx1&&px2<dx2){
             if(py1>dy1&&py1<dy2||py2>dy1&&py2<dy2){
                 return true;
             }
         }


        return false;
    }



}
