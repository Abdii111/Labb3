package org.example.tictactoetwo.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class GameModelTest {
    public GameModel gameModel;

    @Before // Denna metod körs innan varje test
    public void setUp() {
        gameModel = new GameModel(); // Skapa en ny instans av GameModel
    }

    @Test
    @DisplayName("Test player wins")
    public void testPlayerWins() {
        gameModel.makeMove(0, 0, 'X'); // Spelaren gör ett drag
        gameModel.makeMove(1, 0, 'X');
        gameModel.makeMove(2, 0, 'X');

        assertTrue(gameModel.checkWin('X')); // Kontrollera om spelaren vinner
    }

    @Test
    @DisplayName("Test computer wins")
    public void testComputerWins() {
        gameModel.makeMove(0, 0, 'O');
        gameModel.makeMove(0, 1, 'O');
        gameModel.makeMove(0, 2, 'O');

        assertTrue(gameModel.checkWin('O')); // Kontrollera om datorn vinner
    }

    @Test
    @DisplayName("Test draw")
    public void testDraw() {
        // Fyll i brädet på ett sätt som resulterar i oavgjort
        gameModel.makeMove(0, 0, 'X');
        gameModel.makeMove(0, 1, 'O');
        gameModel.makeMove(0, 2, 'X');
        gameModel.makeMove(1, 0, 'O');
        gameModel.makeMove(1, 1, 'X');
        gameModel.makeMove(1, 2, 'O');
        gameModel.makeMove(2, 0, 'O');
        gameModel.makeMove(2, 1, 'X');
        gameModel.makeMove(2, 2, 'O');

        assertTrue(gameModel.isDraw()); // Spelet ska vara oavgjort
    }

    @Test
    @DisplayName("Test invalid move")
    public void testInvalidMove() {
        gameModel.makeMove(0, 0, 'X'); // Spelaren gör ett drag

        assertFalse(gameModel.makeMove(0, 0, 'O')); // Ska inte tillåta drag på en upptagen cell
    }

    @Test
    @DisplayName("Test score increment")
    public void testScoreIncrement() {
        gameModel.incrementPlayerScore(); // Öka spelarens poäng
        gameModel.incrementComputerScore(); // Öka datorns poäng

        assertEquals(1, gameModel.getPlayerScore()); // Spelarens poäng ska vara 1
        assertEquals(1, gameModel.getComputerScore()); // Datorns poäng ska vara 1
    }

    @Test
    public void testMinimax() {
        // Exempeltest för att se om minimax returnerar förväntade värden (valfritt, kan kräva mer inställning)
        int[] bestMove = gameModel.bestMove(); // Hämta det bästa draget
        assertNotNull(bestMove); // Säkerställ att ett drag returneras
        // Ytterligare kontroller kan läggas till baserat på spelstatus och förväntade resultat
    }
}
