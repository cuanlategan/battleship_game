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


    public Ship(Dir dir, Point position, int size){
        this.dir = dir;
        this.position = position;
        this.size = size;
    }


    protected int getMinX(){
        return position.x;
    }

    protected int getMaxX(){
        return dir == Dir.HORIZONTAL ? position.x + size-1: position.x;
    }

    protected int getMinY(){
        return position.y;
    }

    protected int getMaxY(){

        return dir == Dir.VERTICAL ? position.y + size-1: position.y;
    }


}
