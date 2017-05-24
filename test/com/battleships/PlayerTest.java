package com.battleships;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class PlayerTest {

    Board board;
    Player player;

    @Before
    public void setUp(){
        player = new Player();
        board = new Board();
    }


    @Test
    public void placeAllBoats(){
        System.out.println();
        System.out.println("testing placing all boats!");
        player.placeAllShipRandomnly(board);
        board.printBoard();
    }



    @Test(expected =  RuntimeException.class)
    public void failPlaceAllBoats(){
        System.out.println();
        System.out.println("testing placying all boats!");
        board = new Board(2,2);
        player.placeAllShipRandomnly(board);
        board.printBoard();
        System.out.println();
    }
}
