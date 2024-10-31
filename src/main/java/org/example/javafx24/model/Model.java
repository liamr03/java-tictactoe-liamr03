package org.example.javafx24.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    // Board char array for storing the current state
    static char[] board = new char[9];
    static char lastPlayer = '-'; // Initialize lastPlayer

    // Constructor to reset the board at the start
    public Model() {
        resetBoard();
    }

    // Resets the board, setting all cells to empty
    public static void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = '-'; // '-' represents an empty cell
        }
        lastPlayer = 'X'; // Reset lastPlayer when the board is reset
    }

    // Makes a move for the player, returns true if successful
    public static boolean makeMove(int position, char player) {
        if (position >= 0 && position < 9 && board[position] == '-') {
            board[position] = player; // Place the player's symbol
            lastPlayer = player; // Update last player
            return true; // Move was successful
        }
        return false; // Move failed (invalid position or cell occupied)
    }

    // Checks for a win condition
    public static boolean checkWin() {
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}              // Diagonals
        };
        // Check if any winning condition is met
        for (int[] condition : winConditions) {
            if (board[condition[0]] != '-' &&
                    board[condition[0]] == board[condition[1]] &&
                    board[condition[1]] == board[condition[2]]) {
                return true; // A winner is found
            }
        }
        return false; // No winner yet
    }

    // Makes a move for the AI based on strategies
    public static void makeAIMove() {
        // Block potential winning moves for X
        for (int i = 0; i < 9; i++) {
            if (board[i] == '-') {
                board[i] = 'X'; // Temporarily place X
                if (checkWin()) {
                    board[i] = 'O'; // Block the winning move
                    lastPlayer = 'O'; // Update last player
                    return; // Exit after blocking
                }
                board[i] = '-'; // Undo the temporary move
            }
        }

        // Randomly take any available position if the board is mostly empty
        List<Integer> availablePositions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                availablePositions.add(i); // Collect all empty cells
            }
        }

        // Randomly select one available position
        if (!availablePositions.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(availablePositions.size());
            int position = availablePositions.get(randomIndex);
            board[position] = 'O'; // Make the move for AI
            lastPlayer = 'O'; // Update last player
        }
    }

    // Checks for a tie
    public static boolean checkTie() {
        for (char cell : board) {
            if (cell == '-') {
                return false; // At least one cell is empty, no tie yet
            }
        }
        return true; // All cells filled, it's a tie
    }

    // Returns the current board state
    public static char[] getBoard() {
        return board;
    }

    // Retrieves the last player who made a move
    public static char getLastPlayer() {
        return lastPlayer;
    }
}
