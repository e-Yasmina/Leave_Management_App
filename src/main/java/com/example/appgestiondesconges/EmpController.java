package com.example.appgestiondesconges;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class EmpController {
    @FXML
    private AnchorPane BS;

    @FXML
    void move(ActionEvent e) throws IOException {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("ApllyLeavei.fxml"));
        Parent root =fxml.load();
        BS.getChildren().removeAll();
        BS.getChildren().setAll(root);
    }

    @FXML
    protected void lt  (ActionEvent e) throws IOException {
        FXMLLoader fxml1=new FXMLLoader(getClass().getResource("Empi1.fxml"));
        Parent m1 = fxml1.load();
        BS.getChildren().removeAll();
        BS.getChildren().setAll(m1);

    }
    @FXML
    void message(ActionEvent event)throws IOException{
        FXMLLoader s=new FXMLLoader(getClass().getResource(".fxml"));
        Parent ms=s.load();
        BS.getChildren().removeAll();
        BS.getChildren().setAll();
    }
    @FXML
    private AnchorPane  PRBS;
    @FXML
    void LeavesR(ActionEvent ee) throws IOException{
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("LeavesRequest.fxml"));
        Parent m =fxml.load();
        PRBS.getChildren().removeAll();
        PRBS.getChildren().setAll(m);

    }
}