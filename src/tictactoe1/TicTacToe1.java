/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe1;
import java.util.*;


/**
 * @author annamariaalto
 * @version 2016.1220
 * @since 1.8
 */
public class TicTacToe1 {

    /**
     * 
     * @param args
     */
    //public static int x;
    //public static int y;
    
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner (System.in);

        System.out.println("Let's play TIC-TAC-TOE!");

        //Ask the size of the table
        System.out.print("Give the width of the table: ");
        int x = input.nextInt();
        System.out.print("Give the hight of the table: ");
        int y = input.nextInt();    
        
        System.out.print("Give the number of the characters for winning: ");
        int win = input.nextInt();
        
        //Create a table
        int field [][] = new int [y][x];
        
        // 0 = empty element
        // 1 = Player's 1 (user) X-character
        // 2 = Player's 2 (computer) 0-character
        
        //Print empty table
        printField(field);
        
        //Game logic
        boolean game = true;
      
        while(game = checkWin(field, win, y , x, game)){
            int coordX = askCoordinate("Give the coordinate X: ", y, x);
            int coordY = askCoordinate("Give the coordinate Y: ", y, x);
        
            //Checks that player's input is inside the grid
            checkCoordinate(field, coordY, coordX);
                    
            //Prints the new grid with new inputs
            printField(field);
            
            //Checks if any winning rows
            checkWin(field, win, y , x, game);

            //Computer's turn
            aIntelligence(field, y, x);
        
            //Prints the new table with new inputs
            printField(field);
            
            //Checks if any winning rows
            checkWin(field, win, y , x, game);
        
        }
        
        System.out.println("GAME OVER!");
        input.close();
    }
    
    /**
     * Prints the field
     * 
     * Imports the field from the main and prints it
     * 
     * @param array array from the main
     * 
     **/
    public static void printField (int [][] array) {

        int i = 0;  
                
        System.out.println();
        
        //Print the numbers for X-coordinates
        for (int j = 1; j < array[i].length+1; j++) {
            System.out.print("   " + j);
        }
        
        System.out.println();
        
            System.out.print(" ");
            for (int k = 0; k < array[i].length * 4 +1; k++) {   //Print the first horizontal line
                System.out.print("-");
            }
        
        System.out.println();
        
        //Print the array
        for (i = 0; i < array.length; i++) {
            System.out.print(i+1);                          //Printing the numbers for the Y-coordinates
            for (int j = 0; j < array[i].length; j++) {
                switch (array[i][j]) {
                    case 0:
                        System.out.print("|   ");
                        break;                              
                    case 1:
                        System.out.print("| X ");
                        break;
                    case 2:
                        System.out.print("| O ");
                        break;
                }
            } 
            System.out.println("|");
            System.out.print(" ");
            for (int k = 0; k < array[i].length * 4 +1; k++) {   //Prints the horizontal lines
                System.out.print("-");
            }
            System.out.println();
        }
     
        System.out.println();
    }
    
    /**
     * Asks the coordinate from the user
     * 
     * Asks the coordinates and check the values are inside the table
     * and numbers and returns it
     * 
     * @param coordinate String input from the main
     * @param max1 maximum value on y-axis
     * @param max2 maximum value on x-axis
     * @return the input of the user
     */
    public static int askCoordinate (String coordinate, int max1, int max2) {
        
        Scanner input = new Scanner (System.in);
        
        int z = 0;
        boolean k = false;
        
        //Checks for invalid inputs
        do{
            try { 
                System.out.print(coordinate);
                z  = input.nextInt();
                k = true;
            } catch (InputMismatchException e) {
                System.out.println("Input must be numbers!");
                input.next();
            }
        } while (!k);
        
        //Checks the player's input is inside the grid
        while(z > max1 || z > max2) {
            System.out.print("Coordinates need to be inside the table!\n"
                            +coordinate);
            z = input.nextInt();
        }
        input.close();
        return z;
        
    }
    
    /**
     * Computer's turn
     * 
     * Chooses the mext move for O
     * 
     * @param array the field from where to choose the coordinates
     * @param y the vertical length of the table
     * @param x the horizontal length of the table
     * @return the chosen move for O
     */
    public static int aIntelligence (int [][] array, int y, int x) {
         x = (int)(Math.random()*x);
         y = (int)(Math.random()*y);
         
        //Checks dublicates
        while (array[y][x] == 1 || array[y][x] == 2) {
            x = (int)(Math.random()*x);
            y = (int)(Math.random()*y);
        }
        
        array[y][x] = 2;
        
        return array[y][x];
    }

    /**
     * Checks user's input
     * 
     * Checks user's input coordinate are not taken
     * 
     * @param field the field to be checked
     * @param inputY user's Y-input
     * @param inputX user's X-input
     */
    public static void checkCoordinate (int [][] field, int inputY, int inputX) {
        
        Scanner input = new Scanner (System.in);
        
        //Checks coordinates are available
        while(field[inputY-1][inputX-1] == 1 || field[inputY-1][inputX-1] == 2){
            System.out.println("Coordinates are taken. Choose another place.");
            System.out.print("Give the coordinate X: ");
            inputX = input.nextInt();
            System.out.print("Give the coordinate Y: ");
            inputY = input.nextInt();
        }
        
        field[inputY-1][inputX-1] = 1;

        input.close();
    }
    
    /**
     * Checks for winning rows
     * 
     * Checks for rows of characters 
     * 
     * @param field the field to be checked 
     * @param toWin the amount of characters for a win
     * @param y the length of the Y-axis
     * @param x the lenght of the X-axis
     * @param game end the game
     * @return sends false if the game ends
     */
    public static boolean checkWin (int [][] field, int toWin, int y, int x, boolean game) {
         
        while(true){
        //Horizontal lines
        for (int i = 0; i < field.length; i++) {
            
            int hCounterO = 0;
            int hCounterX = 0;
            
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1) {
                    hCounterX++;
                
                    if(hCounterX == toWin) {
                        System.out.println("VOITTAJA ON X! ");
                        game = false;
                    }

                } else {
                    hCounterX = 0;
                }
                    
                if (field[i][j] == 2) {
                    hCounterO++;
                    
                    if(hCounterO == toWin) {
                        System.out.println("VOITTAJA ON O!");
                        game = false;
                    }

                } else {
                    hCounterO = 0;
                }
            }
        }
 
        //Vertical lines       
        for (int i = 0; i < x; i++) {
            
            int vCounterX = 0;
            int vCounterO = 0;

            for (int j = 0; j < y; j++) {
                
                if (field[j][i] == 1) {
                    vCounterX++;
                    
                    if(vCounterX == toWin) {
                        System.out.println("VOITTAJA ON X!");
                        game = false;
                    }
                } else {
                    vCounterX = 0;
                }
                
                if (field[i][j] == 2) {
                    vCounterO++;
                    
                    if(vCounterO == toWin) {
                        System.out.println("VOITTAJA ON O!");
                        game = false;
                    }
                } else {
                    vCounterO = 0;
                }
            }
        }
       
        //Diagonal 1 lines
        
        for (int i = 0; i < y; i++) {
            
            int duCounterX = 0;
            int duCounterO = 0;

            for (int j = 0; j < x; j++) {
                
                switch(field[i][j]) {
                    case 1:
                        duCounterX++;
                        if(i < y-1) {
                            i++;
                        }
                        if(duCounterX == toWin) {
                            System.out.println("VOITTAJA ON X");
                            game = false;
                        }
                        break;
                    case 2:
                        duCounterO++;
                        if (i < y-1) {
                            i++;
                        }
                        if(duCounterO == toWin) {
                            System.out.println("VOITTAJA ON O");
                            game = false;
                        }
                        break;
                    default:
                        duCounterX = 0;
                        duCounterO = 0;
                        break;
                    
                } 
            }
        }
        
        //Diagonal 2 lines
        
         
        for (int i = y - 1; i > 0; i--) {
 
            int ddCounterX = 0;
            int ddCounterO = 0;
           
            for (int j = 0; j < x; j++) {
               
                switch(field[i][j]){
                    case 1:
                        ddCounterX++;
                        if(i > 0)
                            i--;
                        if(ddCounterX == toWin) {
                            System.out.println("VOITTAJA ON VINO2 X!");
                            game = false;
                        }
                        break;
                    case 2:
                        ddCounterO++;
                        if(i > 0)
                            i--;
                        if(ddCounterO == toWin) {
                            System.out.println("VOITTAJA ON VINO2 O!");
                            game = false;
                        }
                        break;
                    default:
                        ddCounterX = 0;
                        ddCounterO = 0;
                        break;
                }
            }
        }
        return game;
    }
   
        
    }       
}
    
