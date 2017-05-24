package com.battleships;

import java.awt.*;

@SuppressWarnings("Duplicates")
public class Main {

    private enum Turn{
        PLAYER_1(), PLAYER_2()
    }


    public static void main(String[] args) {

        Player player1 = new Player();
        Board board1 = new Board();
        player1.placeAllShipRandomnly(board1);

        Player player2 = new Player();
        Board board2 = new Board();
        player2.placeAllShipRandomnly(board2);

        boolean gameOver = false;
        Turn currentTurn = Turn.PLAYER_1;

        while (!gameOver){

            System.out.println("\n======================================================");
            System.out.println(currentTurn + "'s turn");

            switch (currentTurn) {
                case PLAYER_1:
                    {
                        Point bomb = player1.takeChooseBombLocation(board1);
                        System.out.printf("bomb location chosen: x:%d, y:%d\n", bomb.x, bomb.y);

                        if(board2.receiveBomb(bomb)) System.out.println("hit!\n");
                        else System.out.println("miss\n");

                        board2.printBoard();
                        if(!board2.shipsLeft()) gameOver = true;
                    }
                    break;
                case PLAYER_2:
                    {
                        Point bomb = player2.takeChooseBombLocation(board2);
                        System.out.printf("bomb location chosen: x:%d, y:%d\n", bomb.x, bomb.y);

                        if(board1.receiveBomb(bomb)) System.out.println("hit!\n");
                        else System.out.println("miss");

                        board1.printBoard();
                        if(!board1.shipsLeft()) gameOver = true;
                    }
                break;
            }

            System.out.println("======================================================\n\n");

            if(gameOver) System.out.println(currentTurn+" wins!!!");

            currentTurn = currentTurn == Turn.PLAYER_1 ? Turn.PLAYER_2 :Turn.PLAYER_1;
        }

    }
}
