package com.example.appgestiondesconges;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ApllyLeavei {
    @FXML
    private DatePicker sdb;
    @FXML
    private DatePicker edb;
    @FXML
    private Text textal;
    String l_Aplly="INSERT INTO `leave`(`idLeave`, `type`, `dure`, `date_debut`, `date_fin`, `idemp`, `State`) " +
            "VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7])";
    DBUtils con=new DBUtils();
    String sd=String.valueOf(sdb);
    String ed=String.valueOf(edb);
    LocalDate d1 = LocalDate.parse(sd, DateTimeFormatter.ISO_LOCAL_DATE);
    LocalDate d2 = LocalDate.parse(ed, DateTimeFormatter.ISO_LOCAL_DATE);
    Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
    long d = diff.toDays();
    int compteur=0;

    String tp1="sick";
    String tp2="paid";


    public void unpaidl(){
        if(!sd.isEmpty() && !ed.isEmpty()){
            compteur+=1;

            Connection condb=con.getConnection();
            try {
                PreparedStatement ps = condb.prepareStatement(l_Aplly);
                ((PreparedStatement) ps).setString(1, String.valueOf(compteur));
                ((PreparedStatement) ps).setString(2, "unpaid");
                ((PreparedStatement) ps).setInt(3, (int) d);
                ((PreparedStatement) ps).setDate(4, Date.valueOf(sd));
                ((PreparedStatement) ps).setDate(5, Date.valueOf(ed));

                //((PreparedStatement) ps).setString(6,);
                ((PreparedStatement) ps).setString(7,"Waiting");
                ((PreparedStatement) ps).execute();




             } catch (SQLException e) {throw new RuntimeException(e);}
        }


        else{
            textal.setText("Please enter your Login information");
            textal.setFill(Color.RED);
            textal.setFont(Font.font ("Bold", 18));
        }
        }
    @FXML
    protected void paidl(){

    }
    @FXML
    protected void sickl(){

    }


}
