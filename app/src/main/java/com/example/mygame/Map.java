package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.List;

class Map {
    private int width, height;
    private Paint p =new Paint();
    private Hall hall;
    private int countHalls;
    private int index;
    List<Room> map_room = new ArrayList();
    int size;
    Bitmap floor;
    Map(int width, int height, Bitmap floor, int size){
        this.width = width;
        this.height =height;
        this.floor = floor;
        this.size = size;
    }

    private void create(Hall hall){
        int w = hall.width + (int)(Math.random()*100)%4 + 1;
        int h = hall.height + (int)(Math.random()*100)%4 + 1;
        int x = 0, y = 0;
        switch(hall.id){
            case 0:
                x=hall.x-size;
                y=hall.y-h*size;
                break;
            case 1:
                x=hall.x+hall.width*size;
                y=hall.y-size;
                break;
            case 2:
                x=hall.x-size;
                y=hall.y+hall.height*size;
                break;
            case 3:
                x=hall.x-w*size;
                y=hall.y-size;
                break;


        }
        Room room = new Room(x, y,w,h,size,floor);
        map_room.add(room);
        addHall(room);
        room.idOfHalls[(hall.id+2)%4]=false;
    }

    private Room first(){
        int w = 2 + (int)(Math.random()*100)%5;
        int h = 2 + (int)(Math.random()*100)%5;
        return  new Room((width/2), (height/2),w,h,size, floor);
    }

    private Hall createHall(Room r, int id) {
        Hall hall;
        // int w = (int)(Math.random()*100)%2 + 1;
        //int h = (int)(Math.random()*100)%2 + 1;
        int w=1, h=1;
        int x = 0, y = 0;
        switch(id){
            case 0:
                h=4;
                x=r.x+size;
                y=r.y-h*size;
                break;
            case 1:
                w=4;
                x=r.x+r.width*size;
                y=r.y+size;
                break;
            case 2:
                h=4;
                x=r.x+size;
                y=r.y+r.height*size;
                break;
            case 3:
                w=4;
                x=r.x-w*size;
                y=r.y+size;
                break;


        }
        hall = new Hall(x,y,w,h,size,floor);
        return hall;
    }


    void generate(int roomCount){
        countHalls = roomCount-1;
        map_room.clear();
        Room rfirst = first();
        map_room.add(rfirst);
        addHall(rfirst);
    }

    private void addHall(Room r){
        if(!r.hallCreated) {
            r.hallCreated = true;
            r.numberOfHalls = r.numberOfHalls() + 1;
            countHalls -= r.numberOfHalls;
            if (countHalls < 0) r.numberOfHalls += countHalls;
            if (r.numberOfHalls > 0) {
                for (int j = 0; j < r.numberOfHalls; j++) {
                    index = (int) (Math.random() * 100) % 4;
                    if (!r.idOfHalls[index]) index = (index + 1) % 4;
                    if (!r.idOfHalls[index]) index = (index + 1) % 4;
                    if (!r.idOfHalls[index]) index = (index + 1) % 4;
                    if (!r.idOfHalls[index]) index = (index + 1) % 4;
                    r.idOfHalls[index] = false;
                    hall = createHall(r, index);
                    r.room_hall.add(hall);

                }
                for (Hall h : r.room_hall) {
                    create(h);// map_room.add(create(h));
                }
            }
        }
    }


    void drawMap(Canvas canvas){
        for(Room r:map_room){
             p.setColor(Color.GRAY);
           //  canvas.drawRect(r.x, r.y, r.x+r.width*size, r.height*size+r.y, p);
            // p.setColor(Color.BLUE);
            r.drawRoom(canvas);
            for(Hall hall:r.room_hall){
              //   canvas.drawRect(hall.x, hall.y, hall.x+hall.width*size,
              //         hall.height*size+hall.y, p);
                hall.drawHall(canvas);
            }
        }
    }



}
