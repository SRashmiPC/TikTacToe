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
public class TikTakToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter the player: ");
        byte opponent = input.nextByte();
        board.opponent = opponent;
        
        board.startGame();
    }
}
