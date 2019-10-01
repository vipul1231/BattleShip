package com.swiggy.domain;

import lombok.Data;

@Data
public class GameEvent {
    private Player player;
    private TargetBoard targetBoard;
    private Board carrierBoard;
    private String event;
}
