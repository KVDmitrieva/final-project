package com.example.mygame;

import java.util.ArrayList;
import java.util.List;

public class Map {
        int width, height;
        int data[][];
    List<Room> map_room = new ArrayList();
    Map(int width, int height){
        this.width = width;
        this.height = height;
        data = new int[width*height][0];

    }

void generate(int roomCount){
        map_room.clear();
        for(int i = 0; i<roomCount; i++){
            for(int j = 0; j<100; j++){
                int w = 10 + (int)Math.random()*100/3;
                int h = 10 + (int)Math.random()*100/3;
                Room room = new Room(3+(int)Math.random()*1000%(width-w-6),
                        (int)Math.random()*1000%(height-h-6),w,h);

                for(Room r : map_room){
                   if(!room.intersect(r)) {
                        map_room.add(room);
                        break;
                   }

                }
            }
        }
}

}
