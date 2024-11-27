module org.example.cppprojectui {
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires firebase.admin;
    requires com.google.auth;
    requires com.google.auth.oauth2;

    opens org.example.cppprojectui.models to firebase.admin, javafx.fxml;
    opens org.example.cppprojectui to javafx.fxml;
    exports org.example.cppprojectui;
    exports org.example.cppprojectui.models;
    exports org.example.cppprojectui.controllers;
    opens org.example.cppprojectui.controllers to javafx.fxml;
}