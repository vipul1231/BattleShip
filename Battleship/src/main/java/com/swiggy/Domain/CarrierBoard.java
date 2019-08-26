package com.swiggy.Domain;

import java.util.ArrayList;
import java.util.List;


public class CarrierBoard implements Board {

    private List<Ship> shipList = new ArrayList<>();

    private TargetBoard targetBoard = null;

    public CarrierBoard(int size){
        targetBoard = new TargetBoard(size);
    }

    @Override
    public String boardType() {
        return "CARRIER";
    }

    public TargetBoard getTargetBoard() {
        return targetBoard;
    }

    public void addShip(Ship ship){
        shipList.add(ship);
    }
}
