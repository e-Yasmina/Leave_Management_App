package com.example.appgestiondesconges;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SLFController implements Initializable {
    DBUtils con = new DBUtils();
    Connection condb = con.getConnection();
    FileChooser fc=new FileChooser();
    @FXML private Text uploadt;
    @FXML private AnchorPane filew;
    public static  File file;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fc.setInitialDirectory(new File("C:\\"));
    }
    @FXML
    public void uploadf(ActionEvent eu){
        Window stage=filew.getScene().getWindow();
        fc.setTitle("load");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text","*.txt","*.doc"),
        new FileChooser.ExtensionFilter("pdf","*.pdf"),
        new FileChooser.ExtensionFilter("img","*.jpg","*.png","*.gif"));


        file = fc.showOpenDialog(stage);
        if (file != null) {
            uploadt.setText(file.getPath());
        } else  {
            System.out.println("error"); // or something else
        }

            try {
                if (!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
                {
                    System.out.println("not supported");
                    return;
                }
                Desktop desktop = Desktop.getDesktop();
                if (file.exists())         //checks file exists or not
                    desktop.open(file);              //opens the specified file
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    @FXML
    protected void savefile(ActionEvent es){
        String sickl="INSERT INTO `leave`(`idLeave`, `type`, `dure`, `date_debut`, `date_fin`, `idemp`, `State`,`Leave_files`) " +
        "VALUES (?,?,?,?,?,?,?)";
        Statement s = null;
        try {
            s = condb.createStatement();
            ResultSet mq = s.executeQuery(sickl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}