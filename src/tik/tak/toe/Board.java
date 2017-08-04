/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tik.tak.toe;

import java.util.Scanner;

/**
 *
 * @author hp
 */
public class Board {
    
    public static byte winningChances[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}}; 
    public static byte reverseChances[][] = {{0,3,6},{0,4},{0,5,7},{1,3},{1,4,6,7},{1,5},{2,3,7},{2,4},{2,5,6}};
    public byte selection;
    public byte opponent;
    public byte position[];
    Player player ;
    private byte Winner;
    private byte last;
    
    public Board() {
        position = new byte[9];
    }
    
    /*
     * if position = 0 ; empty cell
     * if position = 1 first Player/ computer
     */
    
    
    public void startGame (){
        player = new Player(); // genarate computer player
        selection = 1;
        if(opponent==1){ // if oponent is the first player
            setOpponentSelection();
        }
        
        while(selection!=9){ 
            // if selection == 9 start to get dicition
            setMySelection();
            //consider if it is a win
            if(selection>4 && Arbiter.isWin(position, (byte)1, last)){
                Winner = (byte) 1;
                break;
            }
            // if selection == 9 start to get dicition
            if(selection!=9){
                setOpponentSelection();
                //consider if it is a win
                if(selection>4 && Arbiter.isWin(position, (byte)2, last)){
                    Winner = (byte) 2;
                    break;
                }
            }
            
        }
       selectWinner();
        
    }
    
    public byte[] getCopyPosition(){
        byte copy[] =new byte[9];
        for (byte i=0; i<9; i++){
            copy[i] = position[i];
        }
        return copy;
    }
    
    public void setOpponentSelection(){
        System.out.print("Enter your choice: ");
        Scanner input = new Scanner(System.in); // get inputs
        byte temp = input.nextByte(); //opponent mark is circle
        //validate for already selected cells
        while(position[temp] != 0 ){
            System.out.print("Already selected. Enter another: ");
            temp = input.nextByte(); //opponent mark is circle
        }
        last = temp;
        position[last] = 2;
        selection++; // increased selection
    }
    
    public void setMySelection(){
        if(selection == 1){
            last =player.getFirstIndex();
        }else if(selection ==2){
            last = player.getSecondIndex(last);
        }else{
            System.out.println("Awa");
            byte copy[] = getCopyPosition(); //copy of posion need for calculation
            last = player.getFirstMinMaxAlgo(copy, (byte)1, (byte)selection); //player value always 1 for this
        }
        
        position[last] = 1; // set the computer dicition
        selection++;
        System.out.println("Next: "+last);
    }
    
    public void selectWinner(){
        if(Winner == 0){
            System.out.println("Drawn");
        }if(Winner == 1){
            System.out.println("I won! Try again");
        }else if(Winner == 2){
            System.out.println("Congradz!!!! you won");
        }
    }
    
}
