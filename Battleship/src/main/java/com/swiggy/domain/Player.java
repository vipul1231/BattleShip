package com.swiggy.domain;

import lombok.ToString;

@ToString
public class Player {

    private final String name;

    private final boolean isComputer;

    private Board playerGamingBoard;

    public Player(String name, Boolean isComputer){
        this.name = name;
        this.isComputer = isComputer;
        playerGamingBoard = new CarrierBoard(10);
    }

    public Board getPlayerGamingBoard() {
        return playerGamingBoard;
    }

    public String getName() {
        return name;
    }
}
