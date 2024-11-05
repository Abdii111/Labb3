package org.example.tictactoetwo.view;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameView {
    private Button[][] buttons; // Knappar för spelbrädet
    private GridPane grid; // Layout för spelbrädet
    private Label messageLabel; // Etikett för meddelanden
    private Label scoreLabel; // Etikett för poäng
    private Button restartButton; // Knapp för att återställa det aktuella spelet
    private Button restartAllButton; // Knapp för att återställa alla spel
    private VBox layout; // Huvudlayout för vyn

    public GameView() {
        grid = new GridPane(); // Initiera spelbrädet
        buttons = new Button[3][3]; // Skapa en 3x3 matris av knappar
        messageLabel = new Label(); // Initiera meddelandeetikett
        scoreLabel = new Label("Player: 0 | Computer: 0"); // Initiera poängetikett
        restartButton = new Button("Restart Game"); // Initiera knappen för att återställa spelet
        restartAllButton = new Button("Restart All"); // Initiera knappen för att återställa alla spel

        // Skapa layouten
        layout = new VBox(10, grid, messageLabel, scoreLabel, restartButton, restartAllButton);

        // Sätt knappens egenskaper och lägg till i brädet
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(""); // Initiera knapparna
                buttons[i][j].setPrefSize(100, 100); // Sätt prefererad storlek
                buttons[i][j].setStyle("-fx-font-size: 24;"); // Valfri stil
                grid.add(buttons[i][j], j, i); // Lägg till knappen i brädet
            }
        }
    }

    public void updateButton(int row, int col, String text) {
        buttons[row][col].setText(text); // Uppdatera texten på en knapp
    }

    public void setMessage(String message) {
        messageLabel.setText(message); // Sätt meddelandet i etiketten
    }

    public void updateScore(int playerScore, int computerScore) {
        scoreLabel.setText("Player: " + playerScore + " | Computer: " + computerScore); // Uppdatera poängen
    }

    public Button getButton(int row, int col) {
        return buttons[row][col]; // Hämta knappen på angiven rad och kolumn
    }

    public VBox getLayout() {
        return layout; // Hämta huvudlayouten
    }

    public Button getRestartButton() {
        return restartButton; // Getter för knappen för att återställa spelet
    }

    public Button getRestartAllButton() {
        return restartAllButton; // Getter för knappen för att återställa alla spel
    }
}
