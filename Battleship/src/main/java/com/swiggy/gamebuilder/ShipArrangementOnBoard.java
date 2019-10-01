package com.swiggy.gamebuilder;

import com.swiggy.constants.BattleShipConstants;
import com.swiggy.domain.*;
import com.swiggy.utils.BattleShipUtils;

import java.util.List;
import java.util.Scanner;

/**
 * This class will assign ship on the board
 */
public class ShipArrangementOnBoard {

    private final Board board;

    private final Player player;

    ShipArrangementOnBoard(Board board, Player player){
        this.board = board;
        this.player = player;
    }

    /**
     * This method ask the user and assign a ship to the player carrier grid.
     */
    public void askAndAssignShip(){
        Scanner scanner = new Scanner(System.in);
        List<Ship> ships = player.getPlayerGamingBoard().getShipList();
        System.out.println();
        showShipDetails();
        System.out.println("Carrier board for player: "+player.getName());
        displayBoard();

        System.out.println("Coordinates ex: Rows [A->J], Columns [0->9], (ex. (a0 h) for carrier of length 5 placed horizontally");
        for(Ship ship: ships){
            System.out.print("Assign shipId: ["+ship.getShipShortId()+"]"+", coordinate: ");
            while (true){
                String[] coordinates = scanner.nextLine().split(" ");

                try {
                    String[] placementPosition = BattleShipUtils.returnIndexToShip(coordinates[0],coordinates[1], ship.getLength());
                    String[][] playerGrid = board.getPlayerGrid();

                    String position = placementPosition[0];
                    int constant = Integer.parseInt(placementPosition[1]);
                    int start = Integer.parseInt(placementPosition[2]);
                    int end = (start+ship.getLength())-1;

                    if(position.equals(BattleShipConstants.HORIZONTAL)){
                        for(int i=start;i<=end;i++){
                            playerGrid[constant][i] = ship.getShipShortId();
                            addCoordinatesToShip(constant,i,ship);
                        }
                        ship.setStatus(ShipStatus.ASSIGNED);
                        break;
                    }
                    else {
                        for(int i=start;i<=end;i++){
                            playerGrid[i][constant] = ship.getShipShortId();
                            addCoordinatesToShip(i,constant,ship);
                        }
                        ship.setStatus(ShipStatus.ASSIGNED);
                        break;
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            displayBoard();
            showShipDetails();
        }
    }

    /**
     * Provides ship details and its status
     */
    public void showShipDetails(){
        System.out.println("Showing ship details to player: "+player.getName());
        List<Ship> ships = player.getPlayerGamingBoard().getShipList();
        for (Ship ship : ships){
            System.out.println("ShipId: "+ship.getShipShortId()+", Ship type: "+ship.getShipType()+", Length: "+ship.getLength()+", status: "+ship.getStatus());

        }
    }

    /**
     * This method adds coordinate object to the ship.
     * @param row
     * @param column
     * @param ship
     */
    public void addCoordinatesToShip(int row, int column, Ship ship){
        ShipCoordinates coordinates = new ShipCoordinates();
        coordinates.setRow(row);
        coordinates.setColumn(column);
        ship.addCoordinates(coordinates);
    }

    /**
     * Method to display board.
     */
    public void displayBoard(){
        board.displayCurrentBoard();
    }
}
