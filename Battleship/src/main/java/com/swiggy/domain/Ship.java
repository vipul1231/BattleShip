package com.swiggy.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Ship {
    private String shipShortId;
    private final String shipType;
    private final int length;
    private ShipStatus status = ShipStatus.AVAILABLE;
    private List<ShipCoordinates> shipCoordinates = new ArrayList<>();

    public Ship(String shipType, Integer length){
        this.length = length;
        this.shipType = shipType;
        shipShortId = shipType.substring(0,2)+length;
    }

    public void setStatus(ShipStatus status){
        this.status = status;
    }

    public void addCoordinates(ShipCoordinates coordinates){
        shipCoordinates.add(coordinates);
    }
}
