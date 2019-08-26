package com.swiggy.Domain;

import lombok.Data;

@Data
public class Ship {
    private String shipType;
    private final int length;
    private ShipStatus status;

    Ship(Integer length){
        this.length = length;
    }
}
