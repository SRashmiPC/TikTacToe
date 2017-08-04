/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tik.tak.toe;

import java.util.Random;


/**
 *
 * @author hp
 */
public class Player {
    Player(){}
    
    public int getOpponentChance()
    {
        
        return 0;
    }
    
    public int getAvailableChance()
    {
        return 0;
    }
    
    public int getAvailable()
    {
        return 0;
    }
    
    public int selectRankConner()
    {
        return 0;
    }
    
    public byte getFirstIndex(){
        //return one coner for first selection
        byte[] Coners={0,2,6,8};
        Random genarator = new Random();
        byte index =(byte) genarator.nextInt(4);
        return Coners[index];
    }
    
    public byte getSecondIndex(byte last){
        //return one middle cell index for second selection
        if(last!=4){
            return 4;
        }
        byte[] Middles={0,2,6,8};
        Random genarator = new Random();
        byte index =(byte) genarator.nextInt(4);
        return Middles[index];
        
    }
    
    /*
     * Minimax Algorithm is the algorithm that helps to select correct cell
     * In this case our first minimax method should retun this cell index
     * others should return minimum or maximum value;
     */
    public byte getFirstMinMaxAlgo(byte position[], byte playerValue, byte selection){
        byte chosedValue=0; // value that should be chosed
        boolean thisIsFirst=true; //if it is the first round we should assign value for 
                                  // score and chosedValue;
        byte Score=0;
        if(playerValue == 1){ // here computer is the player
            for (byte i=0; i<9;i++){ //consider all possible moves
                if(position[i]==0){
                    byte myScore=0; // my score is the score for that selection only
                    position[i] = 1; // now position consider as computer selected one
                    /*
                     * we should check if it
                     * is winning move.... 
                     * nor get a score for move
                     */
                    
                    if(Arbiter.isWin(position, playerValue, i)){
                        position[i]=0; //yes it is win NOw remove changes
                        return i; //return index
                    }else{ // if not now get the score
                       myScore = Arbiter.getNumberOfWinningChances(position, playerValue, i);
                    }
                    //new score will be the score from next best selections
                    byte newScore = getMiniMaxAlgo(position, (byte)2, (byte) (selection+1) );
                        //System.out.println("for: "+i+ " myScore: "+myScore+ " newScore "+newScore);
                    newScore+=myScore; //newScore now the returning value
                    System.out.println(i+"scre: "+ newScore);
                    if(newScore >= Score){
                        Score = newScore;// we should get maximum newScore
                        chosedValue = i;
                    }
                    //here we consider first selection round;
                    //no matter what it is first set it as chosed value
                    if(thisIsFirst){
                        Score = newScore;
                        chosedValue = i;
                        thisIsFirst =false;
                    }
                    position[i]=0; //reverse changes
                }
            }
            
        }
        
        return chosedValue;
    }
    
    public byte getMiniMaxAlgo(byte position[], byte playerValue, byte selection){
        byte chosedValue=0; // value that should be chosed
        boolean thisIsFirst = true;
        byte Score=0;
        if(playerValue == 1){
            for (byte i=0; i<9;i++){
                if(position[i]==0){
                    byte myScore=0;
                    position[i] = 1;
                    /*
                     * we should check if it
                     * is winning move.... 
                     * nor get a score for move
                     */
                    if(Arbiter.isWin(position, playerValue, i)){
                        position[i]=0;
                        return 10;
                    }else{
                       myScore = Arbiter.getNumberOfWinningChances(position, playerValue, i);
                    }
                    
                    /*
                     * And we make sure that if some one has go for a
                     * draw we should detect it
                     */
                    if(selection > 7){
                        position[i] = 0;
                        return myScore;
                    }
                    
                    byte newScore = getMiniMaxAlgo(position, (byte)2, (byte) (selection+1));
                    newScore+=myScore;
                    if(newScore >= Score){
                        Score = newScore;
                        chosedValue = i;
                    }
                    //here we consider first selection round;
                    if(thisIsFirst){
                        Score = newScore;
                        chosedValue = i;
                        thisIsFirst =false;
                    }
                    position[i]=0;
                }
            }
            
        }if(playerValue == 2){ // Here we also decides the best possible move for opponent
            for (byte i=0; i<9;i++){
                if(position[i]==0){
                    byte myScore= 0;
                    position[i] = 2;
                    
                    if(Arbiter.isWin(position, playerValue, i)){
                        position[i]=0;
                        return -10; //minus value for oppnent because it is a lose
                    }else{
                       myScore = (Arbiter.getNumberOfWinningChances(position, playerValue, i));
                    }
                    
                    if(selection > 7){
                        position[i] = 0;
                        return myScore;
                    }
                    
                    byte newScore = getMiniMaxAlgo(position, (byte)1, (byte) (selection+1) );
                    newScore -= myScore;
                    if(newScore <= Score){
                        Score = newScore;
                        chosedValue = i;
                    }
                    //here we consider first selection round;
                    if(thisIsFirst){
                        Score = newScore;
                        chosedValue = i;
                        thisIsFirst =false;
                    }
                    position[i]=0;
                }
            }
        }
        
        return Score;
    }
    
}
