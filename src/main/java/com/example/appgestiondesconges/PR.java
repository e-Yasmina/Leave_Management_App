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

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class PR implements Initializable {
    @FXML private TableView<Leave1> LeaveTable;
    @FXML private TableColumn<Leave1,Integer> id;
    @FXML private TableColumn<Leave1,String> type;
    @FXML private TableColumn<Leave1, Long> duration;
    @FXML private TableColumn<Leave1,String> sd;
    @FXML private TableColumn<Leave1,String> ed;
    @FXML private TableColumn<Leave1,String> state;
    @FXML private TableColumn<Leave1,String> idempcl;
    @FXML private TableColumn<Leave1,String> Service;
    @FXML private TableColumn<Leave1,String> file;

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
            DBUtils con=new DBUtils();
            Connection condb=con.getConnection();
            query="SELECT `idLeave`, `type`, `dure`, `date_debut`, `date_fin`, leave.idemp, `State`, `Leave_files`,`service` FROM `leave` INNER JOIN `employee` on leave.idemp=employee.idemp;";//query="SELECT * FROM `leave` ";
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
                        rs.getString("service"),
                        rs.getString("Leave_files")
                        ));
                LeaveTable.setItems(var);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DBUtils con=new DBUtils();
            Connection condb=con.getConnection();
            query="SELECT `idLeave`, `type`, `dure`, `date_debut`, `date_fin`, leave.idemp, `State`, `Leave_files`,`service` FROM `leave` INNER JOIN `employee` on leave.idemp=employee.idemp;";//query="SELECT * FROM `leave` ";
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
                        rs.getInt("dure"),
                        rs.getString("service"),
                        rs.getString("Leave_files")
                        ));
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
                        } else if (leavevar.getIdemp().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true;
                        } else if (leavevar.getDd().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true;
                        } else if (leavevar.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
        Connection condb=con.getConnection();
        id.setCellValueFactory(new PropertyValueFactory<>("idl"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        sd.setCellValueFactory(new PropertyValueFactory<>("dd"));
        ed.setCellValueFactory(new PropertyValueFactory<>("df"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duree"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        idempcl.setCellValueFactory(new PropertyValueFactory<>("idemp"));
        Service.setCellValueFactory(new PropertyValueFactory<>("service"));
        file.setCellValueFactory(new PropertyValueFactory<>("file"));
        LeaveTable.setEditable(true);
        file.setCellFactory(TextFieldTableCell.forTableColumn());
        file.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<Leave1, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Leave1, String> leave1StringCellEditEvent) {
                Leave1 l=leave1StringCellEditEvent.getRowValue();
                File file= new File(leave1StringCellEditEvent.getOldValue());
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
                l.setFile(leave1StringCellEditEvent.getOldValue());


            }
        });
    }


    @FXML
    public void accept_deny(ActionEvent em){
        DBUtils con = new DBUtils();
        Connection condb = con.getConnection();
        refreshfonction(em);
        LeaveTable.setEditable(true);
        state.setCellFactory(TextFieldTableCell.forTableColumn());
        state.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Leave1, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Leave1, String> leave1StringCellEditEvent) {
                Leave1 l=leave1StringCellEditEvent.getRowValue();
                l.setState(leave1StringCellEditEvent.getNewValue());
                String up="UPDATE `leave` SET `State`='"+l.getState()+"'WHERE `idLeave`='"+l.getIdl()+"'";
                try {
                    PreparedStatement ps = condb.prepareStatement(up);
                    ps.execute();
                    ps.close();
                } catch (Exception error) {
                    System.err.println(error);
                }

            }
        });
    }
    @FXML
    public void exitf(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
