module org.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafx24 to javafx.fxml;
    exports org.example.javafx24;
}
