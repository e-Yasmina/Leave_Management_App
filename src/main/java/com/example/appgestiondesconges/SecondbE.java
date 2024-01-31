package com.example.appgestiondesconges;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondbE extends Application{
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginAPP.class.getResource("Sick_leave_files.fxml"));
            FXMLDocumentController fXMLDocumentController = fxmlLoader.getController();
            if(fXMLDocumentController!=null){
                fXMLDocumentController.setGetHostController(getHostServices());
            }
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
