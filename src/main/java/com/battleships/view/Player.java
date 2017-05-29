package com.battleships.view;

import com.battleships.document.Board;
import com.battleships.document.Ship;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;


final class Player {


    void placeAllShipRandomnly(Board board) {
        Ship.SHIPS_ALLOWED.forEach((size, amount) -> placeShipRandom(board, size, amount));
    }


    private void placeShipRandom(Board board, int size, int amount) {

        Integer maxAttempts = 100;
        while (amount > 0){

            final int xPos = ThreadLocalRandom.current().nextInt(board.WIDTH);
            final int yPos = ThreadLocalRandom.current().nextInt(board.HEIGHT);
            final Point pos = new Point(xPos,yPos);

            final Ship.Dir dir = ThreadLocalRandom.current()
                                                  .nextBoolean()
                                                  ? Ship.Dir.HORIZONTAL: Ship.Dir.VERTICAL;

            final Ship ship = new Ship(dir,pos,size);

            if(board.canPlaceShip(ship)){
                board.placeShip(ship);
                --amount;
            }
            else if (--maxAttempts < 0) throw new RuntimeException("Could not place ships in alloted attempts");
        }

    }


    Point chooseBombLocation(Board board) {

        final int xPos = ThreadLocalRandom.current().nextInt(board.WIDTH);
        final int yPos = ThreadLocalRandom.current().nextInt(board.HEIGHT);

        return new Point(xPos,yPos);
    }


}
