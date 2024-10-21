package org.example.javafx24.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import org.example.javafx24.Model.Model;

public class HelloController {

    public void initialize() {
        initializeGame(); // Call this to reset the board and update the view
    }

    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;

    //Initializes the game by resetting the board and using updateView
    public void initializeGame() {
        Model.resetBoard();
        updateView();
    }

    public void resetBoard(ActionEvent actionEvent) {
        Model.resetBoard(); // Call the static resetBoard method from Model
        updateView(); // Update the view to reflect the reset state
    }

    //Check for button clicks
    public void buttonClick(int position) {
        if (Model.makeMove(position, 'X')) {  // Assuming 'X' is the player
            updateView();
            checkGameStatus();
            makeAIMove();
        }
    }

    //Update the buttons based on the current board state
    public void updateView() {
        char[] board = Model.getBoard();
        button1.setText(board[0] == '-' ? "" : String.valueOf(board[0]));
        button2.setText(board[1] == '-' ? "" : String.valueOf(board[1]));
        button3.setText(board[2] == '-' ? "" : String.valueOf(board[2]));
        button4.setText(board[3] == '-' ? "" : String.valueOf(board[3]));
        button5.setText(board[4] == '-' ? "" : String.valueOf(board[4]));
        button6.setText(board[5] == '-' ? "" : String.valueOf(board[5]));
        button7.setText(board[6] == '-' ? "" : String.valueOf(board[6]));
        button8.setText(board[7] == '-' ? "" : String.valueOf(board[7]));
        button9.setText(board[8] == '-' ? "" : String.valueOf(board[8]));
    }

    public void resetGame() {
        Model.resetBoard();
        updateView();
    }

    public void checkGameStatus() {
        if (Model.checkWin()) {
            // Should show like "Player X wins"
        } else if (Model.checkTie()) {
            // Resets the game and shows something like "Tie"
        }
    }

    public void makeAIMove() {
        // Logic for AI move
    }

    public void button1Clicked(ActionEvent actionEvent) {
        buttonClick(0); // Button 1 corresponds to position 0
    }

    public void button2Clicked(ActionEvent actionEvent) {
        buttonClick(1); // Button 2 corresponds to position 1
    }

    public void button3Clicked(ActionEvent actionEvent) {
        buttonClick(2); // Button 3 corresponds to position 2
    }

    public void button4Clicked(ActionEvent actionEvent) {
        buttonClick(3); // Button 4 corresponds to position 3
    }

    public void button5Clicked(ActionEvent actionEvent) {
        buttonClick(4); // Button 5 corresponds to position 4
    }

    public void button6Clicked(ActionEvent actionEvent) {
        buttonClick(5); // Button 6 corresponds to position 5
    }

    public void button7Clicked(ActionEvent actionEvent) {
        buttonClick(6); // Button 7 corresponds to position 6
    }

    public void button8Clicked(ActionEvent actionEvent) {
        buttonClick(7); // Button 8 corresponds to position 7
    }

    public void button9Clicked(ActionEvent actionEvent) {
        buttonClick(8); // Button 9 corresponds to position 8
    }

}
