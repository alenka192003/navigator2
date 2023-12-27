module com.example.navigator2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.navigator2 to javafx.fxml;
    exports com.example.navigator2;
}