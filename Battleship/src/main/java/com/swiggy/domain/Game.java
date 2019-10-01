package com.swiggy.domain;

import lombok.ToString;

import java.util.List;
import java.util.Random;

@ToString
public class Game {

    private int gameId;

    private List<Player> players = null;

    public Game(List<Player> players){
        this.players = players;
        //Generate any random gameId
        gameId = (new Random()).nextInt(900000)+10000;
    }

    public void addPlayers(Player player){
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
