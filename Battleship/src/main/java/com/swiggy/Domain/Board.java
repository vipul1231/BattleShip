package com.swiggy.Domain;

public interface Board {

     String boardType();

     default void addShip(Ship ship){ };
}
