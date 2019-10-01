package com.swiggy.utils;

import com.swiggy.constants.BattleShipConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Utils class for battleship game
 */
public class BattleShipUtils {

    private static Map<String, String> rowToColumnMap = new HashMap<>();

    /**
     * Static block containing alphabet to coordinate mapping.
     */
    static {
        rowToColumnMap.put("A","0");
        rowToColumnMap.put("B","1");
        rowToColumnMap.put("C","2");
        rowToColumnMap.put("D","3");
        rowToColumnMap.put("E","4");
        rowToColumnMap.put("F","5");
        rowToColumnMap.put("G","6");
        rowToColumnMap.put("H","7");
        rowToColumnMap.put("I","8");
        rowToColumnMap.put("J","9");
    }

    /**
     * Provides coordinates for the event triggered
     *
     * @param event
     * @return
     */
    public static int[] provideCoordinates(String event){
        int[] coordinates = new int[2];
        String[] eventArray = event.split("");
        coordinates[0] = Integer.parseInt(rowToColumnMap.get(eventArray[0].toUpperCase()));
        coordinates[1] = Integer.parseInt(eventArray[1]);
        return coordinates;
    }

    /**
     * Return Index where ship needs to be placed in grid.
     *
     * @param startIndex    Start Index
     * @param direction     Direction
     * @param shipLength    Ship Length
     * @return              Array containing index
     * @throws Exception    Any exception
     */
    public static String[] returnIndexToShip(String startIndex, String direction, Integer shipLength) throws Exception{

        String[] placementArray = new String[3];
        String[] front = startIndex.split("");

        /*if(!validateCoordinates(front, back, shipLength)){
            throw new RuntimeException("Invalid coordinates.."+start+"-"+end);
        }*/
        if(direction.equalsIgnoreCase(BattleShipConstants.HORIZONTAL)){
            placementArray[0] = BattleShipConstants.HORIZONTAL;
            placementArray[1] = rowToColumnMap.get(front[0].toUpperCase());
            placementArray[2] = front[1];

        }
        else if(direction.equalsIgnoreCase(BattleShipConstants.VERTICAL)){
            placementArray[0] = BattleShipConstants.VERTICAL;
            placementArray[1] = front[1];
            placementArray[2] = rowToColumnMap.get(front[0].toUpperCase());
        }
        else {
            throw new RuntimeException("entered direction[horizontal/vertical] not matching.");
        }

        return placementArray;
    }

    /*private static boolean validateCoordinates(String[] start, String[] end, Integer shipLength){

        //TODO: Add validation if ship is placed on already existing ship
        if(start[0].equals(end[0])){
            return true;
        }
        else return start[1].equals(end[1]);

    }*/
}
