package com.swiggy.Domain;

import org.junit.Assert;

import java.util.List;

public class Game {

    private String gameId;

    private List<Player> players = null;

    private final Board board;

    Game(Board board){
        this.board = board;
    }

    Game(Board board, List<Player> players){
        this.board = board;
        Assert.assertEquals(players.size(), 2);
        this.players = players;
    }
}
