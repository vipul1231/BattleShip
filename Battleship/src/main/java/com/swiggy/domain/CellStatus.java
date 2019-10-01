package com.swiggy.domain;

public enum CellStatus {
    EMPTY(0,"EMP"),
    MISSILE(1,"MSL"),
    HIT(2, "HIT");

    private int statusFlag;
    private String value;

    CellStatus(int cellStatus, String value){
        this.statusFlag = cellStatus;
        this.value = value;
    }

    public int getStatusFlag() {
        return statusFlag;
    }

    public String getValue() {
        return value;
    }
}
