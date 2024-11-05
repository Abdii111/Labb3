package org.example.tictactoetwo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.tictactoetwo.model.GameModel;
import javafx.event.ActionEvent;
import org.example.tictactoetwo.view.GameView;

public class GameController {
    public Button button00;
    public Button button01;
    public Button button02;
    public Button button10;
    public Button button11;
    public Button button12;
    public Button button20;
    public Button button21;
    public Button button22;
    private GameModel model;
    private GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model; // Tilldela spelmodellen
        this.view = view; // Tilldela spelvyn
        initialize(); // Initiera knappar och lyssnare
    }

    private void initialize() {
        // Sätt åtgärdshanterare för knappar
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int row = i;
                int col = j;
                view.getButton(row, col).setOnAction(e -> handlePlayerMove(row, col));
            }
        }
        view.getRestartButton().setOnAction(e -> resetGame());
        view.getRestartAllButton().setOnAction(e -> resetAllGame()); // Hantera återställning av allt
    }

    @FXML
    private void resetGame() {
        model.resetBoard(); // Återställ spelstatus
        view.setMessage("Game restarted!"); // Visa ett meddelande
        resetButtons(); // Återställ knapptexterna
    }

    @FXML
    private void resetAllGame() {
        model.resetBoard(); // Återställ spelstatus
        model.resetScores(); // Återställ poängen till noll
        view.updateScore(0, 0); // Uppdatera vyn med återställda poäng
        view.setMessage("All game data reset!"); // Visa ett meddelande
        resetButtons(); // Återställ knapptexterna
    }

    private void handlePlayerMove(int row, int col) {
        if (model.makeMove(row, col, 'X')) {
            view.updateButton(row, col, "X"); // Uppdatera knappen med spelarens drag
            checkGameState('X'); // Kontrollera spelstatus efter spelarens drag
            computerMove(); // Låt datorn göra sitt drag
        }
    }

    private void computerMove() {
        int[] move = model.bestMove(); // Hämta bästa drag baserat på minimax-algoritmen
        if (model.makeMove(move[0], move[1], 'O')) {
            view.updateButton(move[0], move[1], "O"); // Uppdatera knappen med datorns drag
            checkGameState('O'); // Kontrollera spelstatus efter datorns drag
        }
    }

    private void checkGameState(char player) {
        if (model.checkWin(player)) {
            view.setMessage(player == 'X' ? "Player Wins!" : "Computer Wins!"); // Visa vinnarmeddelande
            if (player == 'X') {
                model.incrementPlayerScore(); // Öka spelarens poäng
            } else {
                model.incrementComputerScore(); // Öka datorns poäng
            }
            view.updateScore(model.getPlayerScore(), model.getComputerScore()); // Uppdatera poängvyn
            disableButtons(); // Inaktivera knapparna
        } else if (model.isDraw()) {
            view.setMessage("It's a draw!"); // Visa oavgjort meddelande
            view.updateScore(model.getPlayerScore(), model.getComputerScore()); // Uppdatera poängvyn
            disableButtons(); // Inaktivera knapparna
        }
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                view.getButton(i, j).setDisable(true); // Inaktivera varje knapp
            }
        }
    }

    private void resetButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                view.updateButton(i, j, ""); // Rensa texten på knappen
                view.getButton(i, j).setDisable(false); // Aktivera knappar för nästa spel
            }
        }
    }

    public void handleButtonClick(ActionEvent actionEvent) {
        // Denna metod kan lämnas tom om den inte används
    }
}
