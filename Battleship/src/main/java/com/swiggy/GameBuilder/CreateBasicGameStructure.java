package com.swiggy.GameBuilder;

import com.swiggy.Domain.Board;
import com.swiggy.Domain.CarrierBoard;
import com.swiggy.Domain.Game;

import java.io.File;
import java.net.URL;

public class CreateBasicGameStructure implements Runnable{

    private Game game = null;

    @Override
    public void run() {
        Board board = new CarrierBoard(10);
        loadShipOnBoard(board);
    }

    private void loadShipOnBoard(Board board){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL url = classLoader.getResource("ShipInventory");

        File file = new File(url.getFile());
        System.out.println(file.exists());
    }
}
