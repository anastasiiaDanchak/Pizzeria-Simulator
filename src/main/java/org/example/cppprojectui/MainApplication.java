package org.example.cppprojectui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/config_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles/styles.css")).toExternalForm());
            stage.setTitle("Pizza Simulator!");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            System.out.println("exception caught: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}