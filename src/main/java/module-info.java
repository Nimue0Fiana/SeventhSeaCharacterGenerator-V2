module com.example.seventhseagenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.seventhseagenerator to javafx.fxml;
    exports com.example.seventhseagenerator;

    opens com.example.seventhseagenerator.Controllers to javafx.fxml;
    opens com.example.seventhseagenerator.Models to javafx.base;
    exports com.example.seventhseagenerator.Helper;
    opens com.example.seventhseagenerator.Helper to javafx.fxml;
}