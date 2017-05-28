package com.battleships;

import java.awt.*;

@SuppressWarnings("Duplicates")
public class Main {

    private enum Turn{
        PLAYER_1(), PLAYER_2()
    }


    public static void main(String[] args) {

        Player player1 = new Player();
        Board boardP1 = new Board();
        player1.placeAllShipRandomnly(boardP1);

        Player player2 = new Player();
        Board boardP2 = new Board();
        player2.placeAllShipRandomnly(boardP2);

        boolean gameOver = false;
        Turn currentTurn = Turn.PLAYER_1;

        while (!gameOver){

            System.out.println("\n======================================================");

            System.out.println(currentTurn + "'s turn");


            Point bomb = (currentTurn == Turn.PLAYER_1) ? player1.chooseBombLocation(boardP1) :
                                                          player2.chooseBombLocation(boardP2);

            System.out.printf("bomb location chosen: x:%d, y:%d\n", bomb.x, bomb.y);

            Board enemyBoard = (currentTurn == Turn.PLAYER_1) ? boardP2 : boardP1;

            if(enemyBoard.receiveBomb(bomb)) System.out.println("hit!\n");
            else System.out.println("miss\n");

            enemyBoard.printBoard();
            if(!enemyBoard.shipsLeft()) gameOver = true;

            System.out.println("======================================================\n\n");

            if(gameOver) System.out.println(currentTurn+" wins!!!");

            currentTurn = (currentTurn == Turn.PLAYER_1) ? Turn.PLAYER_2 :Turn.PLAYER_1;
        }

    }
}
