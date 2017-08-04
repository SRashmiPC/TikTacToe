package tik.tak.toe;


//import tik.tak.toe.Board;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class Arbiter {
    Board board;
    public Arbiter(Board board) {
        this.board = board; 
    }
    
    public static boolean isWin(byte position[], byte player, byte last){
        byte[] check = Board.reverseChances[last]; 
        byte len = (byte) check.length;
        for(byte i=0; i<len; i++){
            byte chance[] = Board.winningChances[check[i]];
            if(position[chance[0]]==player){
                if(position[chance[1]]==player){
                    if(position[chance[2]]==player){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static byte getNumberOfWinningChances(byte position[], byte player, byte last){
        byte possibilities=0;
        byte[] check = Board.reverseChances[last];
        byte len = (byte) check.length;
        for(byte i=0; i<len; i++){
            byte chance[] = Board.winningChances[check[i]];
            byte count =0;
            for (byte j=0;j<3; j++){
                if(position[chance[j]]==player){
                    count++;
                }else if(position[chance[j]]!=0){
                    count--;
                }
            }
            if(count>1){
                possibilities++;
            }
        }
        return possibilities;
    }
}