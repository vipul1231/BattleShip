package com.swiggy.Domain;

public abstract class Board {

        private int boardSize;

        abstract String boardType();

        Board(Integer size){
                this.boardSize = size;
        }
}
