package com.battleships;

import java.awt.*;

/**
 * Created by cuan on 22.05.17.
 */
public final class Ship {
    final Dir dir;
    private final Point position;
    private final int size;
    private int health;

    public enum Dir{VERTICAL, HORIZONTAL }

    public Ship(Dir dir, Point position, int size){
        this.dir = dir;
        this.position = position;
        this.size = size;
        this.health = size;
    }


    public int getSize(){
        return size;
    }

    public int getX(){
        return position.x;
    }

    public int getY(){
        return position.y;
    }
}
