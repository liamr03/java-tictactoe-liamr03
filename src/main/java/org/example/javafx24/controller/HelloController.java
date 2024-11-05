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
    public Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    public Button[] buttons;

    //Label to store the current score
    public Label scoreXLabel;
    public Label scoreOLabel;

    private int scoreX = 0; // Score for Player X
    private int scoreO = 0; // Score for Player O
    private boolean playerTurn; // Track whose turn it is

    //At start of the game
    public void initialize() {
        buttons = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9};
        playerTurn = true; //Sets the player turn to start
        resetGame(); //Method to reset board and update view
        updateScores(); // Ensure scores are displayed on game start
    }

    //Method for reset button
    public void resetBoard(ActionEvent actionEvent) {
        Model.resetBoard(); // Call the static resetBoard method from Model
        scoreX = scoreO = 0;
        updateScores(); // Update score display
        updateView(); // Update the view to reflect the reset state
    }

    //If it's player turn, you click a button it will check if it's your turn and let you play
    public void buttonClicked(ActionEvent actionEvent) {
        if (playerTurn) {
            int position = Integer.parseInt(((Button) actionEvent.getSource()).getId().substring(6)) - 1; // Get button position from ID
            if (Model.makeMove(position, 'X')) {
                updateView();
                checkGameStatus(); //Check the game status if it's a win, tie or loss before moving forward

                // If it's not a win or tie, make AI's move after player turn
                if (!Model.checkWin() && !Model.checkTie()) {
                    playerTurn = false;
                    makeAIMove();
                }
            }
        }
    }

    private void setButtonsEnabled(boolean enabled) {
        for (Button button : buttons) {
            button.setDisable(!enabled);
        }
    }

    //UpdateView makes the button text empty until its pressed.
    public void updateView() {
        char[] board = Model.getBoard();
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(board[i] == '-' ? "" : String.valueOf(board[i]));
        }
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
