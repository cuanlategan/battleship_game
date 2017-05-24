package com.battleships;

import java.awt.*;

/**
 * Created by cuan on 22.05.17.
 */
public class Board {

    public enum Tiles{
        EMPTY(" "), HIT("X"), VSHIP("|"), HSHIP("-");
        public final String value;

        Tiles(String value){
            this.value = value;
        }
    }

    public final int WIDTH;
    public final int HEIGHT;
    private int shipSegments = 0;

    private Tiles[][] board;


    public Board(){
        WIDTH = 10;
        HEIGHT = 10;
        board = new Tiles[HEIGHT][WIDTH];
        cleanBoard();
    }

    public Board(int width, int height){
        WIDTH = width;
        HEIGHT = height;
        board = new Tiles[HEIGHT][WIDTH];
        cleanBoard();
    }

    protected void cleanBoard(){

        for (int i=0; i<HEIGHT; ++i){
            for (int j=0; j<WIDTH; ++j){
                board[i][j] = Tiles.EMPTY;
            }
        }
    }


    public boolean placeShip(Ship ship){

        if(!withinBounds(ship)) return false;
        else if(tilesOccupied(ship)) return false;

        final int xPos = ship.getX();
        final int yPos = ship.getY();
        final int size = ship.getSize();

        switch (ship.dir) {
            case VERTICAL:
                for (int i=yPos; i < yPos + size; ++i){
                    if(board[i][xPos] != Tiles.EMPTY) throw new RuntimeException(
                            "placeShip tried to place ship on occupied position[x:"+xPos+", y: "+yPos+"]"
                    );
                    board[i][xPos] = Tiles.VSHIP;
                    ++shipSegments;
                }
                break;

            case HORIZONTAL:
                for (int i=xPos; i < xPos + size; ++i){
                    if(board[yPos][i] != Tiles.EMPTY) throw new RuntimeException(
                            "placeShip tried to place ship on occupied position[x:"+xPos+", y: "+yPos+"]"
                    );
                    board[yPos][i] = Tiles.HSHIP;
                    ++shipSegments;
                }
                break;
        }

        return true;
    }


    protected boolean tilesOccupied(Ship ship) {

        final int xPos = ship.getX();
        final int yPos = ship.getY();
        final int size = ship.getSize();

        switch (ship.dir) {
            case VERTICAL:
                for (int i=yPos; i < yPos + size; ++i){
                    if(board[i][xPos] != Tiles.EMPTY) return true;
                }
                break;

            case HORIZONTAL:
                for (int i=xPos; i < xPos + size; ++i){
                    if(board[yPos][i] != Tiles.EMPTY) return true;
                }
                break;
        }

        return false;
    }


    protected boolean withinBounds(Ship ship) {

        final int xPos = ship.getX();
        final int yPos = ship.getY();
        final int size = ship.getSize();

        switch (ship.dir) {
            case VERTICAL:
                return (0 <= xPos && xPos < WIDTH &&
                        0 <= yPos && yPos + size-1 < HEIGHT);

            case HORIZONTAL:
                return (0 <= xPos && xPos + size-1 < WIDTH &&
                        0 <= yPos && yPos < HEIGHT);
        }

        throw new RuntimeException("method withinBounds reached unreachable statement");
    }


    /**
     *
     * @param point where bomb will land
     * @return true if the bomb hit a ship
     */
    protected boolean receiveBomb(Point point){

        Tiles tile = board[point.y][point.x];

        switch (tile) {
            case EMPTY:
                return false;
                //todo add miss tile to board
            case HIT:
                return false;
            case VSHIP:
                board[point.y][point.x] = Tiles.HIT;
                --shipSegments;
                return true;
            case HSHIP:
                board[point.y][point.x] = Tiles.HIT;
                --shipSegments;
                return true;
        }
        return false;
    }


    protected boolean shipsLeft(){
        return shipSegments > 0;
    }

    /**
     *
     * @return String of output to be used for testing
     */
    public String printBoard(){

        StringBuilder result = new StringBuilder();

        for (int i=0; i<HEIGHT; ++i){
            for (int j=0; j<WIDTH; ++j){
                System.out.print(board[i][j].value);
                result.append(board[i][j].value);
            }
            System.out.println();
            result.append("\n");
        }

        return result.toString();
    }
}
