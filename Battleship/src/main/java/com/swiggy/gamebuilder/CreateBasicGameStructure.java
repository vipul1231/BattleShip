package com.swiggy.gamebuilder;

import com.swiggy.domain.Board;
import com.swiggy.domain.Game;
import com.swiggy.domain.Player;
import com.swiggy.domain.Ship;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class CreateBasicGameStructure implements Callable<Game> {

    @Override
    public Game call() {

        try {
            Game game = addPlayersInGame();
            assignShipOnBoard(game);
            return game;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * This method add all requirement for a battle game to play
     *
     * @param board         board
     * @throws Exception    Exception
     */
    private void loadShipOnBoard(Board board) throws Exception{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL url = classLoader.getResource("ShipInventory");

        if(url == null){
            throw new RuntimeException("Empty ShipInventoryFile");
        }

        File file = new File(url.getFile());
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()){
            String[] shipInfo = scanner.next().split(",");
            Ship ship = new Ship(shipInfo[0],Integer.parseInt(shipInfo[1]));
            board.addShip(ship);
        }
    }


    private Game addPlayersInGame() throws Exception{
        //Now create players...
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first player name: ");
        String firstPlayerName = scanner.next();
        Player player1 = new Player(firstPlayerName, false);
        loadShipOnBoard(player1.getPlayerGamingBoard());

        System.out.print("Enter the second player name [Press N for playing with computer]: ");
        String secondPlayerName = scanner.next();
        boolean isComputer = false;
        if(secondPlayerName.equals("N")){
            secondPlayerName = "COMPUTER";
            isComputer = true;
        }
        Player player2 = new Player(secondPlayerName, isComputer);
        loadShipOnBoard(player2.getPlayerGamingBoard());

        Game initiateGame = new Game(new ArrayList<>());
        initiateGame.addPlayers(player1);
        initiateGame.addPlayers(player2);
        return initiateGame;
    }

    /**
     * This method is assign ship to the board.
     *
     * @param game  game
     */
    private void assignShipOnBoard(Game game){
        List<Player> players = game.getPlayers();
        for(Player player: players){
            ShipArrangementOnBoard shipArrangementOnBoard = new ShipArrangementOnBoard(player.getPlayerGamingBoard(), player);
            shipArrangementOnBoard.askAndAssignShip();
        }
    }
}
