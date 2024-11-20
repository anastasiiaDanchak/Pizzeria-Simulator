module org.example.cppprojectui {
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;


    opens org.example.cppprojectui to javafx.fxml;
    exports org.example.cppprojectui;
    exports org.example.cppprojectui.models;
    opens org.example.cppprojectui.models to javafx.fxml;
    exports org.example.cppprojectui.controllers;
    opens org.example.cppprojectui.controllers to javafx.fxml;
}