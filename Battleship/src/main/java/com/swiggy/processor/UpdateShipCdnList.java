package com.swiggy.processor;

import com.swiggy.domain.Board;
import com.swiggy.domain.Ship;
import com.swiggy.domain.ShipCoordinates;
import com.swiggy.domain.ShipEvent;

import java.util.List;

public class UpdateShipCdnList implements Runnable {

    private Board board;

    private ShipEvent shipEvent;

    UpdateShipCdnList(Board board, ShipEvent shipEvent){
        this.board = board;
        this.shipEvent = shipEvent;
    }

    @Override
    public void run() {
        List<Ship> ships = board.getShipList();
        Ship removeSunkShips = null;
        for (Ship ship : ships){
            if(shipEvent.getShipId().equals(ship.getShipShortId())){
                List<ShipCoordinates> shipCoordinates = ship.getShipCoordinates();

                ShipCoordinates coordinatesToRemove = null;
                for(ShipCoordinates coordinates : shipCoordinates){
                    if(coordinates.getColumn() == shipEvent.getColumn()  && coordinates.getRow() == shipEvent.getRow()){
                        coordinatesToRemove = coordinates;
                        break;
                    }
                }

                shipCoordinates.remove(coordinatesToRemove);
                if(shipCoordinates.size() == 0){
                    System.out.println("Player: "+shipEvent.getPlayerName()+" destroyed ship "+ship.getShipType());
                    removeSunkShips = ship;
                }
                break;
            }
        }

        if(removeSunkShips != null){
            board.removeShip(removeSunkShips);
        }
    }
}
