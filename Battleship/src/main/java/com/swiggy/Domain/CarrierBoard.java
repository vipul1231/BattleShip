package com.swiggy.Domain;

import java.util.ArrayList;
import java.util.List;

public class CarrierBoard extends Board {

    private List<Ship> shipList = new ArrayList<>();

    private TargetBoard targetBoard = null;

    CarrierBoard(int size){
        super(size);
        targetBoard = new TargetBoard(size);
    }

    String boardType() {
        return "CARRIER";
    }

    public void addShip(Ship ship){
        shipList.add(ship);
    }
}
