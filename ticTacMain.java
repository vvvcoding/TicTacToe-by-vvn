/* Working Tic-Tac-Toe game where you can play by yourself, against the computer. 
 * Created during class on week 9/23/24. Took 2-3 hours. 
 * Not fully efficient, but works well and handles user input error.
 */

import java.util.*;

public class ticTacMain {

    //check grid
    public static int checkGrid(String[][] grid) {
        for (int i = 0; i < 3; i ++) {
            //check columns
            if ((grid[i][0] == grid[i][1]) && (grid[i][1] == grid[i][2]) && (!grid[i][0].equals("-"))) {
                System.out.println("Winner is Player " + grid[i][1] + "!");
                if (grid[i][1].equals("X")) {
                    return 1;
                } else if (grid[i][1].equals("O")) {
                    return 2;
                }
            }

            //check rows
            if ((grid[0][i] == grid[1][i]) && (grid[1][i] == grid[2][i]) && (!grid[0][i].equals("-"))) {
                System.out.println("Winner is Player " + grid[1][i] + "!");
                if (grid[1][i].equals("X")) {
                    return 1;
                } else if (grid[1][i].equals("O")) {
                    return 2;
                }
            }
        }

        //check diagonals
        if ((grid[0][0] == grid[1][1]) && (grid[1][1] == grid[2][2]) && (!grid[0][0].equals("-"))) {
            System.out.println("Winner is " + grid[0][0] + "!");
            return 3;
        }
        if ((grid[0][2] == grid[1][1]) && (grid[1][1] == grid[2][0]) && (!grid[0][2].equals("-"))) {
            System.out.println("Winner is " + grid[0][2] + "!");
            return 3;
        }

        //check if draw
        int blankCount = 0;
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                if (grid[i][j].equals("-")) {
                    blankCount ++;
                }
            }
        }
        if (blankCount == 0) {
            System.out.println("It's a draw!");
            return -1;
        }
        
        //if no winner, return 0
        return 0;
    }

    //display grid
    public static void displayGrid(String[][] grid) {
        System.out.println("=====");
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=====\n");
    }

    //player add input to grid
    public static void addInputPlayer(int position, String[][] grid, Scanner scnr) {
        int i = position / 10;
        int j = position % 10;
        
        //handle invalid input
        while ((i < 0) || (i > 2) || (j < 0) || (j > 2)) {
            System.out.println("Invalid input.");
            System.out.print("Re-Enter a position (ij): ");
            position = scnr.nextInt();
            i = position / 10;
            j = position % 10;
        }

        //handle "taken" spot
        while (grid[i][j].equals("X") || grid[i][j].equals("O")) {
            System.out.println("Position already taken.");
            System.out.print("Re-Enter a position (ij): ");
            position = scnr.nextInt();
            i = position / 10;
            j = position % 10;
        }

        if (grid[i][j].equals("-")) {
            grid[i][j] = "X";
        }
    }

    //robot add input to grid
    public static void addInputRobot(Random rand, String[][] grid) {
        //choose random position
        Boolean spotFound = false;
        while (!spotFound) {
            int rowChosen = rand.nextInt(3);
            int colChosen = rand.nextInt(3);
            for (int i = 0; i < 3; i ++) {
                for (int j = 0; j < 3; j ++) {
                    if (grid[rowChosen][colChosen].equals("-")) {
                        grid[rowChosen][colChosen] = "O";
                        spotFound = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        //instance of random class
        Random rand = new Random();
        
        //make the grid
        String [][] grid = new String[3][3];
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                grid[i][j] = "-";
            }
        }

        //display grid
        displayGrid(grid);

        //initialize winner
        int winner = checkGrid(grid);
        
        //initialize scanner
        Scanner scnr = new Scanner(System.in);

        //initialize turns
        int turns = 1;

        //choose starting player

        //start game
        while (winner == 0) {

            //who's turn
            if (turns % 2 != 0) {
                //print who's turn
                System.out.println("Player's turn!");

                //get position input
                System.out.print("Enter a position (ij): ");
                int position = scnr.nextInt();

                //add input to grid
                addInputPlayer(position, grid, scnr);
            } else {
                //robot's turn
                System.out.println("AI's turn!");

                //robot add input to grid
                addInputRobot(rand, grid);
            }

            //print new grid
            displayGrid(grid);

            //check grid
            winner = checkGrid(grid);

            //increment turns
            turns ++;
        }

        //close scanner
        scnr.close();
    }
}