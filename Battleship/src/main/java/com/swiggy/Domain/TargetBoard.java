package com.swiggy.Domain;

public class TargetBoard implements Board {

    private final int boardSize;

    public String boardType() {
        return "TARGET";
    }

    public TargetBoard(Integer size){
        this.boardSize = size;
    }
}
