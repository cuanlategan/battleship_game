package com.battleships.view;


import com.battleships.document.Board;

import java.awt.*;
import java.util.List;


public class Main {

    private enum Turn {
        P1("Player-1"), P2("Player-2");
        private final String value;

        Turn(String s) {value = s;}
        public String getValue() { return value; }
    }


    public static String printTiles(List<List<Board.Tile>> tiles) {

        StringBuilder result = new StringBuilder();

        for (java.util.List<Board.Tile> row: tiles) {
            for (Board.Tile tile : row) {
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

        System.out.println(result.toString());

        return result.toString();
    }

    public static void main(String[] args) {

        Player player1 = new Player();
        Board boardP1 = new Board();
        player1.placeAllShipRandomnly(boardP1);

        Player player2 = new Player();
        Board boardP2 = new Board();
        player2.placeAllShipRandomnly(boardP2);

        boolean gameOver = false;
        Turn curTurn = Turn.P1;

        while (!gameOver) {

            System.out.println("\n======================================================");

            System.out.println(curTurn.getValue() + "'s turn");

            Point bomb = (curTurn == Turn.P1) ? player1.chooseBombLocation(boardP1) :
                                                    player2.chooseBombLocation(boardP2);

            System.out.printf("bomb location chosen: x:%d, y:%d\n", bomb.x, bomb.y);

            Board enemyBoard = (curTurn == Turn.P1) ? boardP2 : boardP1;

            if (enemyBoard.receiveBomb(bomb)) System.out.println("hit!\n");
            else System.out.println("miss\n");

            printTiles(enemyBoard.getTiles());

            if (!enemyBoard.shipsLeft()) gameOver = true;

            System.out.println("======================================================\n\n");

            if (gameOver) System.out.println(curTurn.getValue() + " wins!!!");

            curTurn = (curTurn == Turn.P1) ? Turn.P2 : Turn.P1;
        }

    }
}
