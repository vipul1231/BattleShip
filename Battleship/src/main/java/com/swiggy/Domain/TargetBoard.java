package com.swiggy.Domain;

public class TargetBoard extends Board {

    String boardType() {
        return "TARGET";
    }

    public TargetBoard(Integer size){
        super(size);
    }
}
