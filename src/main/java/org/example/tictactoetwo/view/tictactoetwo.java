package org.example.tictactoetwo.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.tictactoetwo.controller.GameController;
import org.example.tictactoetwo.model.GameModel;
import org.example.tictactoetwo.view.GameView;

public class tictactoetwo extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameView view = new GameView();
        GameModel model = new GameModel(); // Initialize the model without difficulty
        new GameController(model, view);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new Scene(view.getLayout(), 320, 400)); // Adjust height as necessary
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
