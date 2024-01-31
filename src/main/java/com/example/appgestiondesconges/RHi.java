package com.example.appgestiondesconges;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RHi extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginAPP.class.getResource("RHi.fxml"));
        Scene scene;
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("LEAVE MANAGEMENT APPLICATION");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
