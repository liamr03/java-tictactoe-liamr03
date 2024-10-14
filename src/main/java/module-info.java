module org.example.javafx24 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafx24 to javafx.fxml;
    exports org.example.javafx24;
}