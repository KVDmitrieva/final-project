package com.example.mygame;

public class Room {
    int x, y, width, height;
    Room(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public boolean intersect(Room r){
        return !(r.x >= (x + width) || x >= (r.x + r.width)
                || r.y >= (y + height) || y >= (r.y + r.height));
    }
}

