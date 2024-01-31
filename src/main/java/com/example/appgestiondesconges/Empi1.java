package com.example.appgestiondesconges;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.scene.control.TableRow;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Empi1 implements Initializable {
    @FXML private TableView<Leave1> LeaveTable;
    @FXML private TableColumn<Leave1,Integer> idcol;
    @FXML private TableColumn<Leave1,String> type;
    @FXML private TableColumn<Leave1, Long> duration;
    @FXML private TableColumn<Leave1,String> sd;
    @FXML private TableColumn<Leave1,String> ed;
    @FXML private TableColumn<Leave1,String> state;
    @FXML private TableColumn<Leave1,String> idempcl;

    @FXML private TextField recherchet;
    String query =null;
    PreparedStatement ps= null;
    ResultSet rs=null;
    public ObservableList<Leave1> var= FXCollections.observableArrayList();
    DBUtils con=new DBUtils();
    Connection condb=con.getConnection();

     @FXML
     private void refreshfonction(ActionEvent erf){
         try {
             var.clear();
             query="SELECT * FROM `leave` WHERE idemp='"+Session.id+"'";

             ps=condb.prepareStatement(query);
             rs=ps.executeQuery();
             while (rs.next()){
                 var.add(new Leave1(
                         rs.getInt("idLeave"),
                         rs.getString("type"),
                         rs.getString("idemp"),
                         rs.getString("state"),
                         rs.getString("date_debut"),
                         rs.getString("date_fin"),
                         rs.getLong("dure"),
                         null,null));
                 LeaveTable.setItems(var);
                 /*FilteredList<Leave1> fvar=new FilteredList<>(var,b ->true);
                 recherchet.textProperty().addListener((observable, oldValue, newValue) -> {
                     fvar.setPredicate(leavevar -> {
                         if (newValue == null || newValue.isEmpty()) {
                             return true;
                         }
                         String lowerCaseFilter = newValue.toLowerCase();

                         if (leavevar.getState().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                             return true; // Filter matches first name.
                         } else
                             return false; // Does not match.
                     });
                 });
                 SortedList<Leave1> sortedData = new SortedList<>(fvar);
                 sortedData.comparatorProperty().bind(LeaveTable.comparatorProperty());
                 LeaveTable.setItems(sortedData);*/
             }

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            query="SELECT * FROM `leave` WHERE idemp='"+Session.id+ "'";
            ps=condb.prepareStatement(query);
            rs=ps.executeQuery();
            while (rs.next()){
                var.add(new Leave1(
                        rs.getInt("idLeave"),
                        rs.getString("type"),
                        rs.getString("idemp"),
                        rs.getString("state"),
                        rs.getString("date_debut"),
                        rs.getString("date_fin"),
                        rs.getInt("dure"),null,null));
                //recherch in table
                FilteredList<Leave1> fvar=new FilteredList<>(var,b ->true);
                recherchet.textProperty().addListener((observable, oldValue, newValue) -> {
                    fvar.setPredicate(leavevar -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (leavevar.getState().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true; // Filter matches first name.
                        } else if (leavevar.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true;
                        } else
                            return false; // Does not match.
                    });
                });
                SortedList<Leave1> sortedData = new SortedList<>(fvar);
                sortedData.comparatorProperty().bind(LeaveTable.comparatorProperty());
                LeaveTable.setItems(sortedData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        idcol.setCellValueFactory(new PropertyValueFactory<>("idl"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        sd.setCellValueFactory(new PropertyValueFactory<>("dd"));
        ed.setCellValueFactory(new PropertyValueFactory<>("df"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duree"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        idempcl.setCellValueFactory(new PropertyValueFactory<>("idemp"));
    }

    @FXML
    public void modifyf(ActionEvent em){
        refreshfonction(em);
        LeaveTable.setEditable(true);
        //idcol.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Leave1, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Leave1, String> leave1StringCellEditEvent) {
                Leave1 l=leave1StringCellEditEvent.getRowValue();
                l.setType(leave1StringCellEditEvent.getNewValue());
                String up="UPDATE `leave` SET `type`='"+l.getType()+"'WHERE `idLeave`='"+l.getIdl()+"'";
                try {
                    PreparedStatement ps = condb.prepareStatement(up);
                    ps.execute();
                    ps.close();
                } catch (Exception error) {
                    System.err.println(error);
                }
            }
        });
        sd.setCellFactory(TextFieldTableCell.forTableColumn());
        sd.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Leave1, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Leave1, String> leave1StringCellEditEvent) {
                Leave1 l=leave1StringCellEditEvent.getRowValue();
                l.setDd(leave1StringCellEditEvent.getNewValue());
                String up="UPDATE `leave` SET `date_debut`='"+l.getDd()+"'WHERE `idLeave`='"+l.getIdl()+"'";
                try {
                    PreparedStatement ps = condb.prepareStatement(up);
                    ps.execute();
                    ps.close();
                } catch (Exception error) {
                    System.err.println(error);
                }
            }
        });
        ed.setCellFactory(TextFieldTableCell.forTableColumn());
        ed.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Leave1, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Leave1, String> leave1StringCellEditEvent) {
                Leave1 l=leave1StringCellEditEvent.getRowValue();
                l.setDf(leave1StringCellEditEvent.getNewValue());
                String up="UPDATE `leave` SET `date_fin`='"+l.getDf()+"'WHERE `idLeave`='"+l.getIdl()+"'";
                try {
                    PreparedStatement ps = condb.prepareStatement(up);
                    ps.execute();
                    ps.close();
                } catch (Exception error) {
                    System.err.println(error);
                }
            }
        });
        duration.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        duration.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Leave1, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Leave1, Long> leave1LongCellEditEvent) {
                Leave1 l=leave1LongCellEditEvent.getRowValue();
                l.setDuree(leave1LongCellEditEvent.getNewValue());
                String up="UPDATE `leave` SET `dure`='"+l.getDuree()+"'WHERE `idLeave`='"+l.getIdl()+"'";
                try {
                    PreparedStatement ps = condb.prepareStatement(up);
                    ps.execute();
                    ps.close();
                } catch (Exception error) {
                    System.err.println(error);
                }
            }
        });
        //state.setCellFactory(TextFieldTableCell.forTableColumn());
        //idempcl.setCellFactory(TextFieldTableCell.forTableColumn());
     }
    @FXML
    public void exitf(ActionEvent event){

    }


}
