package com.battleships;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by cuan on 22.05.17.
 */
public class Board {

    public enum Tile {
        EMPTY("O"), HIT("X"), VSHIP("|"), HSHIP("-");
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
    private int shipSegments = 0;

    private final List<List<Tile>> board;


    public Board() {
        WIDTH = 10;
        HEIGHT = 10;
        board = Collections.unmodifiableList(createBoard());
    }

    public Board(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        board = Collections.unmodifiableList(createBoard());
    }


    private List<List<Tile>> createBoard() {
        List<List<Tile>> result = new ArrayList<>();
        for (int i = 0; i < HEIGHT; ++i) {
            Tile[] row = new Tile[WIDTH];
            Arrays.fill(row, Tile.EMPTY);
            result.add(Arrays.stream(row).collect(Collectors.toList()));
        }
        return result;
    }


    public boolean placeShip(Ship ship) {

        if (!withinBounds(ship)) return false;
        else if (tilesOccupied(ship)) return false;

        final int minX = ship.getMinX();
        final int minY = ship.getMinY();
        final int maxX = ship.getMaxX();
        final int maxY = ship.getMaxY();


        switch (ship.dir) {
            case VERTICAL:
                for (int i = minY; i <= maxY; ++i) {
                    if (board.get(i).get(minX) != Tile.EMPTY) throw new RuntimeException(
                            "placeShip tried to place ship on occupied position[x:" + minX + ", y: " + minY + "]"
                    );
                    board.get(i).set(minX, Tile.VSHIP);
                    ++shipSegments;
                }
                break;

            case HORIZONTAL:
                for (int i = minX; i <= maxX; ++i) {
                    if (board.get(minY).get(i) != Tile.EMPTY) throw new RuntimeException(
                            "placeShip tried to place ship on occupied position[x:" + minX + ", y: " + minY + "]"
                    );
                    board.get(minY).set(i, Tile.HSHIP);
                    ++shipSegments;
                }
                break;
        }

        return true;
    }


    protected boolean tilesOccupied(Ship ship) {

        final int minX = ship.getMinX();
        final int minY = ship.getMinY();
        final int maxX = ship.getMaxX();
        final int maxY = ship.getMaxY();

        switch (ship.dir) {
            case VERTICAL:
                for (int i = minY; i <= maxY; ++i) {
                    if (board.get(i).get(minX) != Tile.EMPTY) return true;
                }
                break;

            case HORIZONTAL:
                for (int i = minX; i <= maxX; ++i) {
                    if (board.get(minY).get(i) != Tile.EMPTY) return true;
                }
                break;
        }

        return false;
    }


    protected boolean withinBounds(Ship ship) {

        final int minX = ship.getMinX();
        final int minY = ship.getMinY();
        final int maxX = ship.getMaxX();
        final int maxY = ship.getMaxY();

        return 0 <= minX && maxX < WIDTH &&
                0 <= minY && maxY < HEIGHT;
    }


    /**
     * @param point where bomb will land
     * @return true if the bomb hit a ship
     */
    protected boolean receiveBomb(Point point) {

        Tile tile = board.get(point.y).get(point.x);

        switch (tile) {
            case EMPTY:
                return false;
            //todo add miss tile to board
            case HIT:
                return false;
            case VSHIP:
                board.get(point.y).set(point.x, Tile.HIT);
                --shipSegments;
                return true;
            case HSHIP:
                board.get(point.y).set(point.x, Tile.HIT);
                --shipSegments;
                return true;
        }
        return false;
    }


    protected boolean shipsLeft() {
        return shipSegments > 0;
    }

    /**
     * @return String of output to be used for testing
     */
    public String printBoard() {


        StringBuilder result = new StringBuilder();

        for (List<Tile> row: board) {
            for (Tile tile : row) {
                result.append(tile.getValue());
            }
            result.append("\n");
        }


        /*
        board.stream().forEach(row -> {
            row.stream().forEach(tile -> result.append(tile.getValue()));
            result.append("\n");
        });
        */

        return result.toString();
    }

}
