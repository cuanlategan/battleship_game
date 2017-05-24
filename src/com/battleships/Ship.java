package com.battleships;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuan on 22.05.17.
 */
public final class Ship {


    final Dir dir;
    private final Point position;
    private final int size;
    private int health;

    static protected final Map<Integer, Integer> maxShips = Collections.unmodifiableMap(createMap());


    final private static Map<Integer, Integer> createMap() {
        final Map<Integer, Integer> result = new HashMap<>(4);
        // <Size of ship, amount allowed>
        result.put(2,3);
        result.put(3,3);
        result.put(4,2);
        result.put(6,1);
        return result;
    }

    public enum Dir{VERTICAL, HORIZONTAL }

    /*
    public enum MAX_NUM_SHIP{
        SIZE_2(3), SIZE_3(3), SIZE_4(2), SIZE_6(1);
        public final int value;

        MAX_NUM_SHIP(int value){
            this.value = value;
        }

    }
    */

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
