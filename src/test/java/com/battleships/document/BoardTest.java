package com.battleships.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.battleships.view.Main;
import org.junit.Test;
import java.awt.Point;


public class BoardTest {

    private Board board;
    private final Ship.Dir horizontal = Ship.Dir.HORIZONTAL;
    private final Ship.Dir vertical = Ship.Dir.VERTICAL;


    @Test
    public void goodBoardDirectionality(){

        System.out.println("Testing good board directionality");
        String empty = Board.Tile.EMPTY.getValue();
        String expected = empty+empty+"\n"+empty+empty+"\n"+empty+empty+"\n";
        int width = 2;
        int height = 3;
        board = new Board(width, height);
        assertEquals("Testing board directionality failed", expected, Main.printTiles(board.getTiles()));
    }


    @SuppressWarnings("SuspiciousNameCombination")
    @Test
    public void badBoardDirectionality(){

        System.out.println("Testing bad board directionality");
        String empty = Board.Tile.EMPTY.getValue();
        String expected = empty+empty+"\n"+empty+empty+"\n"+empty+empty+"\n";
        int width = 2;
        int height = 3;
        board = new Board(height, width);
        assertFalse("Testing board directionality failed", expected.equals(Main.printTiles(board.getTiles())));
    }


    @Test
    public void goodPlaceShip() {

        System.out.println("Testing ship placement by filling a small board");
        board = new Board(5,4);
        board.placeShip(new Ship(horizontal, new Point(0, 0), 4));
        board.placeShip(new Ship(horizontal,  new Point(0,1), 4));
        board.placeShip(new Ship(horizontal,  new Point(0,2), 4));
        board.placeShip(new Ship(horizontal,  new Point(0,3), 4));
        board.placeShip(new Ship(vertical, new Point(4,0), 4));
        Main.printTiles(board.getTiles());
        System.out.println();
    }


    @Test
    public void badPlaceShip() {

        System.out.println("Testing ship placement by trying to add a ship to a full board");
        board = new Board(5,4);
        board.placeShip(new Ship(horizontal, new Point(0,0), 4));
        board.placeShip(new Ship(horizontal, new Point(0,1), 4));
        board.placeShip(new Ship(horizontal, new Point(0,2), 4));
        board.placeShip(new Ship(horizontal, new Point(0,3), 4));
        board.placeShip(new Ship(vertical,   new Point(4,0), 4));

        Main.printTiles(board.getTiles());

        for (int i=0; i<4; ++i){
            for (int j=0; j<5; ++j){
                Ship ship = new Ship(horizontal,new Point(j,i),1);
                boolean result = board.canPlaceShip(ship);
                assertFalse("should not of been able to place ship", result);
            }
        }
    }


    @Test
    public void receiveBombHorzonalShip() {

        board = new Board();
        Point point = new Point(0,0);
        Ship ship = new Ship(horizontal,point,5);
        board.placeShip(ship);

        for(int i=ship.getMinX(); i <= ship.getMaxX(); ++i){
            Point bomb = new Point(i,0);
            boolean result = board.receiveBomb(bomb);
            assertTrue("receiveBomb (i:"+i+") should of been true", result);
        }

        for(int i=0; i < board.HEIGHT; ++i) {
            for (int j = ship.getMaxX(); j < board.WIDTH; ++j) {
                Point bomb = new Point(j, i);
                boolean result = board.receiveBomb(bomb);
                assertFalse("receiveBomb (i:"+i+") should of been false", result);
            }
        }
    }

    @Test
    public void receiveBombVerticalShip() {

        board = new Board();
        Point point = new Point(0,0);
        Ship ship = new Ship(vertical,point,5);
        board.placeShip(ship);

        for(int i=ship.getMinY(); i <= ship.getMaxY(); ++i){
            Point bomb = new Point(0,i);
            boolean result = board.receiveBomb(bomb);
            assertTrue("receiveBomb (i:"+i+") should of been true", result);
        }

        for(int i=ship.getMaxY()+1; i < board.HEIGHT; ++i) {
            for (int j = ship.getMaxX()+1; j < board.WIDTH; ++j) {
                Point bomb = new Point(j, i);
                boolean result = board.receiveBomb(bomb);
                assertFalse("receiveBomb (i:"+i+") should of been false", result);
            }
        }
    }

}
