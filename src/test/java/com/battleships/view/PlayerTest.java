package com.battleships.view;

import com.battleships.document.Board;
import com.battleships.document.Ship;
import com.battleships.view.Main;
import com.battleships.view.Player;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;


public class PlayerTest {

    private Board board;
    private Player player;

    @Before
    public void setUp(){
        player = new Player();
        board = new Board();
    }


    @Test
    public void placeAllBoats(){
        System.out.println("testing placing all boats!");
        player.placeAllShipRandomnly(board);
        Main.printTiles(board.getTiles());

        long expectedLives = Ship.SHIPS_ALLOWED.entrySet().stream()
                                                          .map(s -> s.getKey() * s.getValue())
                                                          .reduce(0, (a,b) -> a+b);

        assertEquals("Ship lives should be equal", expectedLives, board.getShipLives());
    }


    @Test(expected =  RuntimeException.class)
    public void failPlaceAllBoats(){
        board = new Board(2,2);
        player.placeAllShipRandomnly(board);
        Main.printTiles(board.getTiles());
    }
}
