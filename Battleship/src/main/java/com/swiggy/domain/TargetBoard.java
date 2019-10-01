package com.swiggy.domain;

import lombok.ToString;

@ToString
public class TargetBoard extends PlayerGridCreator implements Board {

    public String boardType() {
        return "TARGET";
    }

    @Override
    public String[][] getPlayerGrid() {
        return playerGrid;
    }

    public TargetBoard(Integer size){
        super(size);
        init_2();
    }
}
