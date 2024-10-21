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
}
