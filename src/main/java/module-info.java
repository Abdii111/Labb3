module org.example.tictactoetwo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tictactoetwo to javafx.fxml;
    exports org.example.tictactoetwo.controller;
    opens org.example.tictactoetwo.controller to javafx.fxml;
    exports org.example.tictactoetwo.view;
    opens org.example.tictactoetwo.view to javafx.fxml;
}