module org.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafx24 to javafx.fxml;
    exports org.example.javafx24;
    exports org.example.javafx24.controller;
    opens org.example.javafx24.controller to javafx.fxml;
    exports org.example.javafx24.model;
    opens org.example.javafx24.model to javafx.fxml;
}
