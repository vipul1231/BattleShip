package com.swiggy.domain;

import java.util.HashMap;
import java.util.Map;

abstract class PlayerGridCreator {

    protected String[][] playerGrid = null;

    protected Map<Integer, String> mapRowToCharacter = new HashMap<>();

    private int size;

    protected PlayerGridCreator(int size) {
        playerGrid = new String[size][size];
        this.size = size;
        init_1();
        init_2();
   }

   private void init_1(){
       for(int i=0;i<size;i++){
           for(int j=0;j<size;j++){
               playerGrid[i][j] = CellStatus.EMPTY.getValue();
           }
       }
   }

    protected void init_2(){
        mapRowToCharacter.put(0,"A");
        mapRowToCharacter.put(1,"B");
        mapRowToCharacter.put(2,"C");
        mapRowToCharacter.put(3,"D");
        mapRowToCharacter.put(4,"E");
        mapRowToCharacter.put(5,"F");
        mapRowToCharacter.put(6,"G");
        mapRowToCharacter.put(7,"H");
        mapRowToCharacter.put(8,"I");
        mapRowToCharacter.put(9,"J");
    }

    public void displayCurrentBoard(){
        System.out.println();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(playerGrid[i][j]+"["+mapRowToCharacter.get(i)+j+"] ");
            }
            System.out.println();
        }
    }
}
