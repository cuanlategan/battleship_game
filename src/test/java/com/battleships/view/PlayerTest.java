package com.battleships.view;

import com.battleships.document.Board;
import com.battleships.document.Ship;

import org.junit.Before;
import org.junit.Test;
import java.awt.Point;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class PlayerTest {

    private Board board;
    private Player player;

    @Before
    public void setUp(){
        player = new Player();
        board = new Board();
    }


    @Test
    public void chooseBombLocation(){

        System.out.println("Testing AI choosen bomblocation is within bounds");

        Point loc = player.chooseBombLocation(board);

        boolean withinBounds = (0 <= loc.x && loc.x < board.WIDTH &&
                                0 <= loc.y && loc.y < board.HEIGHT);

        assertTrue("chosen bomb location outside of board", withinBounds );
    }


    @Test
    public void placeAllShips(){
        System.out.println("testing placing all ships randomly");
        player.placeAllShipRandomnly(board);
        Main.printTiles(board.getTiles());

        long expectedLives = Ship.SHIPS_ALLOWED.entrySet().stream()
                                                          .map(s -> s.getKey() * s.getValue())
                                                          .reduce(0, (a,b) -> a+b);

        assertEquals("Ship lives should be equal", expectedLives, board.getShipLives());
    }


    @Test(expected =  RuntimeException.class)
    public void failPlaceAllShips(){

        System.out.println("testing it is possible to place all ships");

        board = new Board(2,2);
        player.placeAllShipRandomnly(board);
        Main.printTiles(board.getTiles());

    }
}
