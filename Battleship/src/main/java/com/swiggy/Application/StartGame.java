package com.swiggy.Application;

import com.swiggy.GameBuilder.CreateBasicGameStructure;

public class StartGame {

    public static void main(String[] args){
        CreateBasicGameStructure start = new CreateBasicGameStructure();
        Thread th1 = new Thread(start);
        th1.start();
    }
}
