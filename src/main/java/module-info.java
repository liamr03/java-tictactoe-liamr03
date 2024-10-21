module org.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafx24 to javafx.fxml;
    exports org.example.javafx24;
    exports org.example.javafx24.Controller;
    opens org.example.javafx24.Controller to javafx.fxml;
    exports org.example.javafx24.Model;
    opens org.example.javafx24.Model to javafx.fxml;
}
