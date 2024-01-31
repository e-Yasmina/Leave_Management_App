package com.example.appgestiondesconges;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onSingUPButtonClick(ActionEvent e) throws IOException {

        Parent n= FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene r =new Scene(n) ;
        Stage m=(Stage)((Node) e.getSource()).getScene().getWindow();
        m.setScene(r);
        m.show();
    }
    @FXML
    protected void exit(ActionEvent r){
        Stage stage = (Stage)((Node) r.getSource()).getScene().getWindow();
        stage.close();
    }
}