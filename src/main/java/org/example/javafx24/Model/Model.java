package org.example.javafx24.Model;

public class Model {

    static char[] board = new char[9];

    //Resetting board at the start
    public Model() {
        resetBoard();
    }

    //Resets the board, setting all cells to empty
    public static void resetBoard(){
        for (int i = 0; i < 9; i++) {
            board[i] = '-';  // '-' represents an empty cell
        }
    }

    public static boolean makeMove(int position, char player) {
        if (position >= 0 && position < 9 && board[position] == '-'){
            board[position] = player;
            return true;
        }
        return false;
    }

    public static boolean checkWin(){
        int[][] winConditions = {
                {0, 1, 2}, // Row 1
                {3, 4, 5}, // Row 2
                {6, 7, 8}, // Row 3
                {0, 3, 6}, // Column 1
                {1, 4, 7}, // Column 2
                {2, 5, 8}, // Column 3
                {0, 4, 8}, // Diagonal 1
                {2, 4, 6}  // Diagonal 2
        };

        for (int[] condition : winConditions) {
            if (board[condition[0]] != '-' &&
                    board[condition[0]] == board[condition[1]] &&
                    board[condition[1]] == board[condition[2]]) {
                return true;  // There's a winner
            }
        }
        return false;  // No winner yet
    }

    public static boolean checkTie() {
        for (char cell : board) {
            if (cell == '-') {
                return false;  // At least one cell is empty, no tie yet
            }
        }
        return true;  // All cells filled, it's a tie
    }

    public static char[] getBoard() {
        return board;
    }
}
