package org.example.tictactoetwo.model;

public class GameModel {
    private char[][] board; // Spelbrädet
    private int playerScore; // Poäng för spelaren
    private int computerScore; // Poäng för datorn

    public GameModel() {
        board = new char[3][3]; // Initiera spelbrädet
        playerScore = 0; // Sätta spelarens poäng till 0
        computerScore = 0; // Sätta datorns poäng till 0
    }

    public boolean makeMove(int row, int col, char player) {
        // Kontrollera att draget är giltigt
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '\0') {
            board[row][col] = player; // Gör draget
            return true; // Draget genomfört framgångsrikt
        }
        return false; // Ogiltigt drag
    }

    public boolean checkWin(char player) {
        // Kontrollera rader och kolumner för vinst
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true; // Vinstvillkor uppfyllt
            }
        }
        // Kontrollera diagonaler för vinst
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public boolean isDraw() {
        // Kontrollera om det finns tomma celler kvar
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') return false; // Tom cell hittad
            }
        }
        return true; // Inga tomma celler, måste vara oavgjort
    }

    public void resetBoard() {
        board = new char[3][3]; // Rensa spelbrädet
    }

    public void resetScores() {
        playerScore = 0; // Nollställ spelarens poäng
        computerScore = 0; // Nollställ datorns poäng
    }

    public int getPlayerScore() {
        return playerScore; // Hämta spelarens poäng
    }

    public int getComputerScore() {
        return computerScore; // Hämta datorns poäng
    }

    public void incrementPlayerScore() {
        playerScore++; // Öka spelarens poäng
    }

    public void incrementComputerScore() {
        computerScore++; // Öka datorns poäng
    }

    // Minimax-algoritm för att beräkna datorns bästa drag
    public int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (checkWin('O')) return 10; // Datorn vinner
        if (checkWin('X')) return -10; // Spelaren vinner
        if (isDraw()) return 0; // Oavgjort

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE; // Bästa poäng för datorn
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '\0') {
                        board[i][j] = 'O'; // Datorns drag
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = '\0'; // Ångra draget
                        bestScore = Math.max(score, bestScore); // Välj bästa poäng
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE; // Bästa poäng för spelaren
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '\0') {
                        board[i][j] = 'X'; // Spelarens drag
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = '\0'; // Ångra draget
                        bestScore = Math.min(score, bestScore); // Välj bästa poäng
                    }
                }
            }
            return bestScore;
        }
    }

    public int[] bestMove() {
        int bestValue = Integer.MIN_VALUE; // Bästa poäng
        int[] move = new int[2]; // Hålla koll på bästa drag

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    board[i][j] = 'O'; // Datorns drag
                    int moveValue = minimax(board, 0, false);
                    board[i][j] = '\0'; // Ångra draget

                    if (moveValue > bestValue) {
                        move[0] = i; // Rad
                        move[1] = j; // Kolumn
                        bestValue = moveValue; // Uppdatera bästa poäng
                    }
                }
            }
        }
        return move; // Returnera bästa drag
    }
}
