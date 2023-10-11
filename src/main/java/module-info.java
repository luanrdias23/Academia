module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.model to javafx.base;

    opens com.example to javafx.fxml;
    exports com.example;
    exports com.example.model;
}
