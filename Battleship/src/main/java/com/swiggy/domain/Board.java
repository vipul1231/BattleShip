package com.swiggy.domain;

import java.util.ArrayList;
import java.util.List;

public interface Board {

     String boardType();

     String[][] getPlayerGrid();

     default void addShip(Ship ship){ }

     default void removeShip(Ship ship) { }

     void displayCurrentBoard();

     default TargetBoard getTargetBoard() {return new TargetBoard(0);}

     default List<Ship> getShipList() { return new ArrayList<>(); }
}
