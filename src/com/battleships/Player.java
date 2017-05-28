package com.battleships;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.battleships.Ship.*;

/**
 * Created by cuan on 24.05.17.
 */
public final class Player {


    public void placeAllShipRandomnly(Board board){

        Ship.maxShips.entrySet()
                .stream()
                .forEach( ship -> placeShipRandom(board, ship.getKey(),ship.getValue()));

    }


    private void placeShipRandom(Board board, int size, int amount){

        Integer maxAttempts = 100;
        while (amount > 0){

            final int xPos = ThreadLocalRandom.current().nextInt(board.WIDTH);
            final int yPos = ThreadLocalRandom.current().nextInt(board.HEIGHT);
            final Point pos = new Point(xPos,yPos);

            final Ship.Dir dir = ThreadLocalRandom.current()
                                                  .nextBoolean()
                                                  ? Ship.Dir.HORIZONTAL: Ship.Dir.VERTICAL;

            if(board.placeShip(new Ship(dir,pos,size))) --amount;
            else --maxAttempts;

            if (maxAttempts < 0) throw new RuntimeException("Could not place ships in alloted attemps");
        }
    }


    public Point chooseBombLocation(Board board){
        final int xPos = ThreadLocalRandom.current().nextInt(board.WIDTH);
        final int yPos = ThreadLocalRandom.current().nextInt(board.HEIGHT);
        return new Point(xPos,yPos);
    }

}
