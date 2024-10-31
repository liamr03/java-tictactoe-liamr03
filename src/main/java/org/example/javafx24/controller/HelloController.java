package org.example.javafx24.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.util.Duration;
import org.example.javafx24.model.Model;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class HelloController {

    //Importing all buttons from view
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;

    //Label to store the current score
    public Label scoreXLabel;
    public Label scoreOLabel;

    private int scoreX = 0; // Score for Player X
    private int scoreO = 0; // Score for Player O
    private boolean playerTurn; // Track whose turn it is

    //At start of the game
    public void initialize() {
        playerTurn = true;
        Model.resetBoard();
        updateView();
        updateScores(); // Ensure scores are displayed on game start
    }

    //Method for reset button
    public void resetBoard(ActionEvent actionEvent) {
        Model.resetBoard(); // Call the static resetBoard method from Model
        resetScores(); // Reset scores
    }

    //Reset score method for reset button
    private void resetScores() {
        scoreX = 0; // Reset score for Player X
        scoreO = 0; // Reset score for Player O
        updateScores(); // Update score display
        updateView(); // Update the view to reflect the reset state
    }

    //Check if any button on the board is clicked and take the b
    public void buttonClicked(ActionEvent actionEvent) {
        if (playerTurn) {
            int position = Integer.parseInt(((Button) actionEvent.getSource()).getId().substring(6)) - 1; // Get button position from ID
            if (Model.makeMove(position, 'X')) {
                updateView();
                checkGameStatus();
                playerTurn = false;
                if (!Model.checkWin() && !Model.checkTie()) {
                    makeAIMove();
                }
            }
        }
    }

    private void setButtonsEnabled(boolean enabled) {
        button1.setDisable(!enabled);
        button2.setDisable(!enabled);
        button3.setDisable(!enabled);
        button4.setDisable(!enabled);
        button5.setDisable(!enabled);
        button6.setDisable(!enabled);
        button7.setDisable(!enabled);
        button8.setDisable(!enabled);
        button9.setDisable(!enabled);
    }
        //UpdateView makes the button text empty until its pressed.
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

    public void checkGameStatus() {
        if (Model.checkWin() || Model.checkTie()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Over");

            if (Model.checkWin()) {
                alert.setHeaderText("Player " + Model.getLastPlayer() + " Wins!");
                if (Model.getLastPlayer() == 'X') {
                    scoreX++;
                } else {
                    scoreO++;
                }
            } else {
                alert.setHeaderText("It's a Tie!");
            }

            updateScores(); // Update the score display
            alert.showAndWait();

            //Added a pause because I had a problem with AI being able to play after restart
            PauseTransition pause = new PauseTransition(Duration.seconds(.1)); // .1 seconds delay
            pause.setOnFinished(event -> resetGame()); // Reset the game after the pause
            pause.play(); // Start the pause
        }
    }

    public void resetGame() {
        playerTurn = true;
        Model.resetBoard();
        updateView();
        setButtonsEnabled(true);
    }

    private void updateScores() {
        scoreXLabel.setText("X: " + scoreX);
        scoreOLabel.setText("O: " + scoreO);
    }

    public void makeAIMove() {
        if (!playerTurn) { // AI should only play when it's not the player's turn
            setButtonsEnabled(false);
            Model.makeAIMove(); // AI makes the move
            updateView();
            checkGameStatus();
            if (!Model.checkWin() && !Model.checkTie()) {
                playerTurn = true; // Switch back to the player's turn
            }
            setButtonsEnabled(true); // Enable buttons after AI's move
        }
    }
}
