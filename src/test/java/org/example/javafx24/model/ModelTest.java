package org.example.javafx24.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    @BeforeEach
    void setUp() {
        Model.resetBoard(); // Återställ spelplanen före varje test
    }

    @Test
    void testThreeInARowWin() {
        // Simulerar att spelare X får tre i rad
        Model.makeMove(0, 'X'); // Lägg X på position 0
        Model.makeMove(1, 'X'); // Lägg X på position 1
        Model.makeMove(2, 'X'); // Lägg X på position 2

        // Kontrollera om X har vunnit
        assertTrue(Model.checkWin(), "Spelaren X borde vinna med tre i rad.");
    }

    @Test
    void testThreeInAColumnWin() {
        // Player X wins with three in a column (first column)
        Model.makeMove(0, 'X');
        Model.makeMove(3, 'X');
        Model.makeMove(6, 'X');

        assertTrue(Model.checkWin(), "Player X should win with three in a column (first column).");
    }

    @Test
    void testDiagonalWinFromTopLeft() {
        // Player X wins with a diagonal from top-left to bottom-right
        Model.makeMove(0, 'X');
        Model.makeMove(4, 'X');
        Model.makeMove(8, 'X');

        assertTrue(Model.checkWin(), "Player X should win with a diagonal from top-left to bottom-right.");
    }

    @Test
    void testCannotPlaceOnOccupiedSpot() {
        // Simulera att spelare X lägger en symbol på en position
        boolean firstMove = Model.makeMove(4, 'X');
        assertTrue(firstMove, "Första flytten borde vara tillåten.");

        // Försök att lägga en annan symbol på samma plats
        boolean secondMove = Model.makeMove(4, 'O');
        assertFalse(secondMove, "Det borde inte vara tillåtet att lägga en symbol på en redan upptagen plats.");
    }

    @Test
    public void testCheckTieReturnsTrueWhenBoardIsFull() {
        // Arrange: Set up a full board with no winning line (a tie)
        Model.board = new char[]{'X', 'O', 'X', 'O', 'X', 'X', 'O', 'X', 'O'};

        // Act: Check for tie
        boolean isTie = Model.checkTie();

        // Assert: Ensure checkTie returns true, indicating a tie
        assertTrue(isTie, "checkTie should return true when the board is full and no player has won.");
    }
}
