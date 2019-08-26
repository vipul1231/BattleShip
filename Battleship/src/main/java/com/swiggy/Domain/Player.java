package com.swiggy.Domain;

import java.util.List;

public class Player {

    List<Game> gamesPlayed;

    private final String name;

    private final Game game;

    public Player(String name, Game game){
        this.game = game;
        this.name = name;
    }
}
