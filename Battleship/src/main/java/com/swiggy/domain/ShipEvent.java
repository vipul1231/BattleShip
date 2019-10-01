package com.swiggy.domain;


import lombok.Data;

@Data
public class ShipEvent {
    private String shipId;
    private String playerName;
    private int row;
    private int column;
}
