package com.swiggy.processor;

import com.swiggy.domain.Game;
import com.swiggy.domain.GameEvent;
import com.swiggy.domain.ReplyEvent;
import com.swiggy.domain.TargetBoard;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class responsibility is to process game.
 */
public class GameProcessor implements Runnable{

    private final Game game;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private boolean isPlayerOneChance = true;

    private boolean isPlayerTwoChance = false;

    private static boolean stopTheGame = false;

    public GameProcessor(Game game){
        this.game = game;

    }

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        while (true){
            synchronized (this){

                if(stopTheGame){
                    break;
                }

                if(Thread.currentThread().getName().equalsIgnoreCase("Player_1") && isPlayerOneChance){
                    System.out.println("Player "+game.getPlayers().get(0).getName()+", below is the target board for you.");
                    TargetBoard playerTwoTargetBoard = game.getPlayers().get(1).getPlayerGamingBoard().getTargetBoard();
                    playerTwoTargetBoard.displayCurrentBoard();

                    System.out.print("Player: "+game.getPlayers().get(0).getName()+", enter coordinates to fire missile [ex d7]: ");
                    String missileCoordinates = scanner.nextLine();

                    GameEvent gameEvent = new GameEvent();
                    gameEvent.setEvent(missileCoordinates);
                    gameEvent.setTargetBoard(playerTwoTargetBoard);
                    gameEvent.setCarrierBoard(game.getPlayers().get(1).getPlayerGamingBoard());
                    gameEvent.setPlayer(game.getPlayers().get(0));
                    processEvents(gameEvent);
                }
                else if(Thread.currentThread().getName().equalsIgnoreCase("Player_2") && isPlayerTwoChance){

                    System.out.println("Player "+game.getPlayers().get(1).getName()+", below is the target board for you.");
                    TargetBoard playerOneTargetBoard = game.getPlayers().get(0).getPlayerGamingBoard().getTargetBoard();
                    playerOneTargetBoard.displayCurrentBoard();

                    System.out.print("Player: "+game.getPlayers().get(1).getName()+", enter coordinates to fire missile: ");
                    String missileCoordinates = scanner.nextLine();

                    GameEvent gameEvent = new GameEvent();
                    gameEvent.setEvent(missileCoordinates);
                    gameEvent.setTargetBoard(playerOneTargetBoard);
                    gameEvent.setCarrierBoard(game.getPlayers().get(0).getPlayerGamingBoard());
                    gameEvent.setPlayer(game.getPlayers().get(1));
                    processEvents(gameEvent);
                }
                else {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Process game events.
     *
     * @param gameEvent game event
     */
    private void processEvents(GameEvent gameEvent){
        ProcessGameEvent processGameEvent = new ProcessGameEvent(gameEvent);
        Future<ReplyEvent> future = executorService.submit(processGameEvent);

        try {
            if(!future.get().isHitTheTarget()){
                if(Thread.currentThread().getName().equals("Player_1")){
                    isPlayerTwoChance = true;
                    isPlayerOneChance = false;
                }

                else {
                    isPlayerOneChance = true;
                    isPlayerTwoChance = false;
                }
                this.notifyAll();
                this.wait();
            }
            else {
                Thread.sleep(1000);
                //System.out.println(Thread.currentThread().getName()+" Current ship list:"+gameEvent.getCarrierBoard().getShipList().size());
                if(gameEvent.getCarrierBoard().getShipList().size() == 0) {
                    System.out.println("Congrats, Player :"+gameEvent.getPlayer().getName()+" wins the game.");
                    stopTheGame = true;
                    this.notifyAll();
                    Thread.currentThread().interrupt();
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
