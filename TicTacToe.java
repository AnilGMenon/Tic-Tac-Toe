/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ANIL MENON
 */
import java.util.Scanner;

public class TicTacToe {

    private int nRows;
    private int nColumns;
    private int numToWin;
    private char grid[][];
    private char turn;
    private TicTacToeEnum gameState;
    private int nMarks = 0;

    /** user provides intialTurn however the board is set to be a 3x3. */
    public TicTacToe(char initialTurn) {
       
        this(3, 3, 3, initialTurn);
    }

    /** sets the rows, columns, numToWin and turn to the values given by user. */
    public TicTacToe(int nRows, int nColumns, int numToWin, char initialTurn) {
        
        if (nRows <0 || nColumns < 0 || numToWin < 0) {
            throw new IllegalArgumentException("The nRows or nColumns or numToWin is smaller than threshold");
        }

        this.nRows = nRows;
        this.nColumns = nColumns;
        this.numToWin = numToWin;
        this.turn = initialTurn;
        grid = new char[this.nRows][this.nColumns];
        nMarks = 0;
        reset(initialTurn);
    }

    /** Resets the board to an empty board. */
    public void reset(char initialTurn) {

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                grid[i][j] = ' ';
            }
        }     
 
    }

    /** Returns the turn. */
    public char getTurn() {
       
        return this.turn;
    }

    /**Finds game state by calling findWinner() method. */
    public TicTacToeEnum getGameState() {
       
        return findWinner();
    }

    /** Returns if player X or O won or if it is a draw or if it is still in progress. */
    private TicTacToeEnum charToEnum(char player) {
       
        if (player == 'X') {
            return TicTacToeEnum.X_WON;
        }
        if (player == 'O') {
            return TicTacToeEnum.O_WON;
        }
        if (player == ' ') {
            return TicTacToeEnum.IN_PROGRESS;
        } 
        else{
            return TicTacToeEnum.DRAW;
        }
    }

    /** Alternates player from X to O. */
    public TicTacToeEnum takeTurn(int row, int column) {
        
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("the rows and columns are less than 0");
        }
        if(grid[row][column] != ' '){
             throw new IllegalArgumentException("this space is filled");
        }
        nMarks +=1;
        
        if (turn == 'X') {
            grid[row][column] = turn;
            turn = 'O';

        } else if (turn == 'O') {
            grid[row][column] = turn;
            turn = 'X';
        }
        gameState = findWinner();
        return gameState;

    }

    /** Determines who is the winner or if it is a draw.*/
    private TicTacToeEnum findWinner() {
       
       char player = 'd';
       char check = ' ';
       int X_count = 0;
       int O_count = 0;
       int X1_count = 0;
       int O1_count = 0;
       
       for (int i= 0; i < nRows; i++){
           for (int j = 0; j< nColumns; j++){
               if (grid[i][j] == 'X'){
                   X_count++;
                   O_count = 0;
               }
               if (grid[i][j] == 'O'){
                   O_count++;
                  X_count = 0;
               }
               if (X_count == numToWin){
                  
                   return TicTacToeEnum.X_WON;
               }
               if (O_count == numToWin){
                  
                  return TicTacToeEnum.O_WON;
               }
               
           }
           
           X_count = 0;
           O_count = 0;
       }
       
       for (int j= 0; j < nColumns; j++){
           for (int i = 0; i< nRows; i++){
               if (grid[i][j] == 'X'){
                   X1_count++;
                   O1_count = 0;
               }
               if (grid[i][j] == 'O'){
                   O1_count++;
                   X1_count = 0;
               }
               if (X1_count == numToWin){
                   return TicTacToeEnum.X_WON;
               }
               if (O1_count ==numToWin){
                   
                   return TicTacToeEnum.O_WON;
               }
              
           }
            X1_count = 0;
            O1_count = 0;
       }
       
       if(nMarks==(nColumns*nRows)){
           player = 'd';
       }
       else{
           player = ' ';
       }
        return charToEnum(player);
    }

    /** Returns a string implementation of the TicTacToe board. */
    public String toString() {
       
        String newGrid = "";

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns-1; j++) {
                newGrid +=  grid[i][j] + " | ";
            }
            newGrid += grid[i][nColumns -1] +" | \n";
        }

         return newGrid;
    }
    public static void main(String args[]) {
        TicTacToe game = new TicTacToe('X');
        Scanner scanner = new Scanner(System.in);
        do {
        System.out.println(game.toString());
        System.out.println(game.getTurn() +
        ": Where do you want to mark? Enter row column");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        scanner.nextLine();
        game.takeTurn(row, column);

        } while (game.getGameState() == TicTacToeEnum.IN_PROGRESS);
        System.out.println( game.getGameState());

 }
    
}
