package com.battleships.document;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public final class Ship {

    public enum Dir{VERTICAL, HORIZONTAL}

    private final Dir dir;
    private final Point position;
    private final int size;

    public static final Map<Integer, Integer> SHIPS_ALLOWED = Collections.unmodifiableMap(createMap());


    public Ship(Dir dir, Point position, int size) {

        this.dir = dir;
        this.position = position;
        this.size = size;
    }


    private static Map<Integer, Integer> createMap() {
        // <Size of ship, amount allowed>
        final Map<Integer, Integer> result = new HashMap<>();
        result.put(2,3);
        result.put(3,3);
        result.put(4,2);
        result.put(6,1);
        return result;
    }


    Dir getDir() {
        return dir;
    }

    int getMinX(){
        return position.x;
    }

    int getMinY(){
        return position.y;
    }

    int getMaxX(){
        return dir == Dir.HORIZONTAL ? position.x + size-1: position.x;
    }

    int getMaxY() {
        return dir == Dir.VERTICAL ? position.y + size-1: position.y;
    }


}
