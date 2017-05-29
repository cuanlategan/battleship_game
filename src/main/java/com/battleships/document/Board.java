package com.battleships.document;

import java.awt.Point;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;



public class Board {

    // Specifications only allow player to have one board, so a "MISS" tile is omitted as there is no way to
    // represent a miss unless later on the spec's change to allow a player to have a "tracking board"
    public enum Tile {
        //      u00B7 == middle-dot                u2014 == m-dash
        EMPTY(" \u00B7"), HIT(" X"), VSHIP(" |"), HSHIP(" \u2014");
        private final String value;

        Tile(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

    }

    public final int WIDTH;
    public final int HEIGHT;

    private int shipLives = 0;
    private final List<List<Tile>> tiles;


    public Board() {
        WIDTH = 10;
        HEIGHT = 10;
        tiles = Collections.unmodifiableList(createTiles());
    }

    public Board(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        tiles = Collections.unmodifiableList(createTiles());
    }


    private List<List<Tile>> createTiles() {

        List<List<Tile>> result = new ArrayList<>();

        for (int i = 0; i < HEIGHT; ++i) {
            Tile[] row = new Tile[WIDTH];
            Arrays.fill(row, Tile.EMPTY);
            result.add(Arrays.stream(row).collect(Collectors.toList()));
        }
        return result;
    }


    public void placeShip(Ship ship) {

        final int minX = ship.getMinX();
        final int minY = ship.getMinY();
        final int maxX = ship.getMaxX();
        final int maxY = ship.getMaxY();

        Tile tile = (ship.getDir() == Ship.Dir.VERTICAL) ? Tile.VSHIP : Tile.HSHIP;

        for (int i = minY; i <= maxY; ++i) {
            for (int j = minX; j <= maxX ; ++j) {
                if (tiles.get(i).get(j) != Tile.EMPTY) throw new RuntimeException(
                        "placeShip tried to place ship on occupied position[x:" + minX + ", y: " + minY + "]"
                );
                tiles.get(i).set(j, tile);
                ++shipLives;
            }
        }
    }


    public boolean canPlaceShip(Ship ship) {

        final int minX = ship.getMinX();
        final int minY = ship.getMinY();
        final int maxX = ship.getMaxX();
        final int maxY = ship.getMaxY();

        boolean withinBounds = (0 <= minX && maxX < WIDTH &&
                                0 <= minY && maxY < HEIGHT);

        if(!withinBounds) return false;

        for (int i = minY; i <= maxY; ++i) {
            for (int j = minX; j <= maxX; ++j) {
                if (tiles.get(i).get(j) != Tile.EMPTY) return false;
            }
        }

        return true;
    }


    /**
     * @param point where bomb will land
     * @return true if the bomb hit a ship, false otherwise
     */
    public boolean receiveBomb(Point point) {

        Tile tile = tiles.get(point.y).get(point.x);

        switch (tile) {
            case EMPTY:
                return false;
            case HIT:
                return false;
            case VSHIP:
                tiles.get(point.y).set(point.x, Tile.HIT);
                --shipLives;
                return true;
            case HSHIP:
                tiles.get(point.y).set(point.x, Tile.HIT);
                --shipLives;
                return true;
        }

        return false;
    }


    public boolean shipsLeft() {
        return shipLives > 0;
    }

    public int getShipLives() {
        return shipLives;
    }

    public List<List<Tile>> getTiles() {
        return tiles;
    }

}
