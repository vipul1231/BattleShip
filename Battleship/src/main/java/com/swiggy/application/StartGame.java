package com.swiggy.application;

import com.swiggy.domain.Game;
import com.swiggy.gamebuilder.CreateBasicGameStructure;
import com.swiggy.processor.GameProcessor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class StartGame {

    public static void main(String[] args){
        CreateBasicGameStructure start = new CreateBasicGameStructure();
        FutureTask<Game> futureTask = new FutureTask<>(start);
        Thread th1 = new Thread(futureTask);
        th1.start();

        try {
            Game game = futureTask.get();
            GameProcessor gameProcessor =  new GameProcessor(game);
            //Initiate two threads one for player one and other for player 2

            Thread thread1 = new Thread(gameProcessor);
            Thread thread2 = new Thread(gameProcessor);

            thread1.setName("Player_1");
            thread2.setName("Player_2");

            thread1.start();
            thread2.start();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
