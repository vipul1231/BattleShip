package com.swiggy.processor;

import com.swiggy.domain.*;
import com.swiggy.utils.BattleShipUtils;

import java.util.concurrent.Callable;

public class ProcessGameEvent implements Callable<ReplyEvent> {

    private final GameEvent gameEvent;

    public ProcessGameEvent(GameEvent gameEvent){
        this.gameEvent = gameEvent;
    }

    @Override
    public ReplyEvent call() throws Exception {
        int[] coordinates = BattleShipUtils.provideCoordinates(gameEvent.getEvent());
        TargetBoard targetBoard = gameEvent.getTargetBoard();
        Board carrierBoard = gameEvent.getCarrierBoard();
        String[][] targetBoardMatrix = targetBoard.getPlayerGrid();

        String[][] playerGrid = carrierBoard.getPlayerGrid();
        ReplyEvent replyEvent = new ReplyEvent();

        if(playerGrid[coordinates[0]][coordinates[1]].equals("EMP")){
            targetBoardMatrix[coordinates[0]][coordinates[1]] = CellStatus.MISSILE.getValue();
            System.out.println("Player: "+gameEvent.getPlayer().getName()+", your missile missed target!");
        }
        else {
            String shipId = playerGrid[coordinates[0]][coordinates[1]];
            targetBoardMatrix[coordinates[0]][coordinates[1]] = CellStatus.HIT.getValue();
            playerGrid[coordinates[0]][coordinates[1]] = CellStatus.HIT.getValue();
            System.out.println("Player: "+gameEvent.getPlayer().getName()+", It's a HIT.");
            replyEvent.setHitTheTarget(true);

            updateShipCoordinates(gameEvent.getPlayer().getName(),shipId,coordinates[0],coordinates[1],carrierBoard);
        }
        //targetBoard.displayCurrentBoard();
        return replyEvent;
    }

    /**
     * This method will update the coordinate array present in ship object and also identify whether entire ship is destroyed or not.
     *
     * @param playerName    Player name
     * @param shipId        Ship Id
     * @param row           row
     * @param column        column
     * @param board         board
     */
    public void updateShipCoordinates(String playerName, String shipId, int row, int column, Board board){
        ShipEvent shipEvent = new ShipEvent();
        shipEvent.setColumn(column);
        shipEvent.setRow(row);
        shipEvent.setPlayerName(playerName);
        shipEvent.setShipId(shipId);

        UpdateShipCdnList updateShipCdnList = new UpdateShipCdnList(board, shipEvent);
        Thread thread = new Thread(updateShipCdnList);
        thread.start();
    }

}
