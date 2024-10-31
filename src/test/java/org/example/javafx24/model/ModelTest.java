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
    void testCannotPlaceOnOccupiedSpot() {
        // Simulera att spelare X lägger en symbol på en position
        boolean firstMove = Model.makeMove(4, 'X');
        assertTrue(firstMove, "Första flytten borde vara tillåten.");

        // Försök att lägga en annan symbol på samma plats
        boolean secondMove = Model.makeMove(4, 'O');
        assertFalse(secondMove, "Det borde inte vara tillåtet att lägga en symbol på en redan upptagen plats.");
    }
}
