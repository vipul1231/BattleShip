package com.swiggy.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class CarrierBoard extends PlayerGridCreator implements Board {

    private List<Ship> shipList = new ArrayList<>();

    private TargetBoard targetBoard = null;

    private final int boardSize;

    public CarrierBoard(int size){
        super(size);
        targetBoard = new TargetBoard(size);
        this.boardSize = size;
    }

    @Override
    public String boardType() {
        return "CARRIER";
    }

    @Override
    public String[][] getPlayerGrid() {
        return playerGrid;
    }

    public TargetBoard getTargetBoard() {
        return targetBoard;
    }

    public void addShip(Ship ship){
        shipList.add(ship);
    }

    public void removeShip(Ship ship){
        shipList.remove(ship);
    }

    public List<Ship> getShipList() {
        return shipList;
    }
}
