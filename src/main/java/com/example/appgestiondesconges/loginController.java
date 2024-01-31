package com.example.appgestiondesconges;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static java.lang.String.valueOf;
import static java.time.LocalDate.parse;
import static java.time.temporal.ChronoUnit.DAYS;


public class loginController implements Initializable {

    @FXML
    private Text text1;
    @FXML
    private TextField un;
    @FXML
    private PasswordField pw;
    //public static String idemp ;
    Session idemp1;
    DBUtils con = new DBUtils();
    Connection condb = con.getConnection();



    public void LOGIN(ActionEvent e) {
        if (!un.getText().isBlank() && !pw.getText().isBlank()) {
            Session.id = un.getText();
            String loginv = "SELECT COUNT(1) FROM `employee` WHERE idemp='" + un.getText() + "' AND login='" + pw.getText() + "'";
            String f = "SELECT fonction FROM `employee` WHERE idemp='" + un.getText() + "' AND login='" + pw.getText() + "'";
            try {
                Statement s = condb.createStatement();
                ResultSet loginq = s.executeQuery(loginv);
                Statement sf = condb.createStatement();
                ResultSet fq = sf.executeQuery(f);

                while (loginq.next()) {
                    if (loginq.getInt(1) == 1) {
                        while (fq.next()) {
                            if (Objects.equals(fq.getString("fonction"), "Employee")) {
                                Parent n = FXMLLoader.load(getClass().getResource("Empi.fxml"));
                                Scene r = new Scene(n);
                                Stage m = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                m.setScene(r);
                                m.show();
                            } else {
                                if (Objects.equals(fq.getString("fonction"), "Responsible")) {
                                    Parent n = FXMLLoader.load(getClass().getResource("Ri.fxml"));
                                    Scene r = new Scene(n);
                                    Stage m = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                    m.setScene(r);
                                    m.show();
                                } else if (Objects.equals(fq.getString("fonction"), "First responsible")) {
                                    Parent n = FXMLLoader.load(getClass().getResource("PRi.fxml"));
                                    Scene r = new Scene(n);
                                    Stage m = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                    m.setScene(r);
                                    m.show();
                                } else {
                                    Parent n = FXMLLoader.load(getClass().getResource("RHi.fxml"));
                                    Scene r = new Scene(n);
                                    Stage m = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                    m.setScene(r);
                                    m.show();
                                }
                            }

                        }

                    } else {
                        text1.setText("The login information you entered is incorrect ");
                        text1.setFill(Color.RED);
                        text1.setFont(Font.font("Bold", 18));
                    }
                }

            } catch (Exception ee) {
                ee.printStackTrace();
            }

        } else {
            text1.setText("Please enter your Login information");
            text1.setFill(Color.RED);
            text1.setFont(Font.font("Bold", 18));
        }
    }

    @FXML
    private AnchorPane loginS;
    @FXML
    public void ForNullP()throws IOException{
        FXMLLoader fxml1=new FXMLLoader(getClass().getResource("FP_NULLP.fxml"));
        Parent m1 = fxml1.load();
        loginS.getChildren().removeAll();
        loginS.getChildren().setAll(m1);
    }
    @FXML
    public void  exit_pp(ActionEvent eepp) throws IOException {
        Parent n = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene r = new Scene(n);
        Stage m = (Stage) ((Node) eepp.getSource()).getScene().getWindow();
        m.setScene(r);
        m.show();
    }
    ///////////////////FP_NULLP
    @FXML
    private  Text message;
    @FXML
    private TextField Messae_id;
    //"+String.valueOf(Messae_id)+"
    //////////////////////////////////////////////////message/////////////////////////////////////////////////////
    public void send_message(ActionEvent esm){
        String newmessage="INSERT INTO `message`(`idM`, `content`) VALUES (?,?)";
        String maxid="SELECT max(idM) FROM `message`";
        String max = null;
        try {
            Statement s = condb.createStatement();
            ResultSet psc = s.executeQuery(maxid);
            while (psc.next()){
                max = psc.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        compteur = Integer.parseInt(max)+1;
        try {
            PreparedStatement ps = condb.prepareStatement(newmessage);
            ps.setString(1, valueOf(compteur));
            ps.setString(2, "Employee'"+Messae_id.getText()+"' need a new password");
            ps.execute();
            ps.close();
        } catch (Exception error) {
            System.err.println(error);
        }

    }

    ////////////////////////////////Message///////////////
    @FXML
    protected void recieve_messsage(ActionEvent ms) throws SQLException {
        String m="SELECT `content` FROM `message`";
        Statement s = condb.createStatement();
        //String msg = null;
        ResultSet mq = s.executeQuery(m);
        while (mq.next()){
                //msg+=mq.getString(1);
            message.setText(mq.getString(1));
        }

        }

        //message.setFill(Color.NAVY);
        //message.setFont(Font.font("Bold", 18));



    //////////////EmpController
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
    void set_message(ActionEvent event) throws IOException, SQLException {
        FXMLLoader s=new FXMLLoader(getClass().getResource("Message.fxml"));
        Parent ms=s.load();
        BS.getChildren().removeAll();
        BS.getChildren().setAll(ms);
    }

    @FXML
    void LeavesR(ActionEvent ee) throws IOException{
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("LeavesRequest.fxml"));
        Parent m =fxml.load();
        BS.getChildren().removeAll();
        BS.getChildren().setAll(m);
    }


    ////////////////ApllyLeave
    @FXML private DatePicker sdb;
    @FXML private DatePicker edb;

    @FXML private Text textal;
    String l_Aplly = "INSERT INTO `leave`(`idLeave`, `type`, `dure`, `date_debut`, `date_fin`, `idemp`, `State`) " +
            "VALUES (?,?,?,?,?,?,?)";
    String chek_idl="SELECT max(`idLeave`) FROM `leave`";
    String maxlu="SELECT sum(`dure`) FROM `leave` where `type`=\"unpaid\" and `State` in (\"waiting\",\"accepted\") and idemp='"+Session.id+"'";

    String maxlp="SELECT sum(`dure`) FROM `leave` where `type`=\"paid\" and `State` in (\"waiting\",\"accepted\") and idemp='"+Session.id+"'";
    String sds= valueOf(sdb);
    String eds= valueOf(edb);
    static int compteur = 0;
    @FXML
    public void unpaidleave(ActionEvent epl) throws SQLException {


        if (!sds.isEmpty() && !eds.isEmpty()) {
            Statement sm = null;
            ResultSet pscm=null;
            try {
                sm = condb.createStatement();
                pscm = sm.executeQuery(maxlu);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(pscm.next()){
                if (pscm.getInt(1) < 30) {
                    LocalDate date1 = sdb.getValue();
                    LocalDate date2 = edb.getValue();
                    //long diffrence = DAYS.between(sdb.getValue(), sdb.getValue());
                    long days = ChronoUnit.DAYS.between(date1, date2);
                    Connection condb = con.getConnection();
                    String maxid = null;
                    try {
                        Statement s = condb.createStatement();
                        ResultSet psc = s.executeQuery(chek_idl);
                        while (psc.next()) {
                            maxid = psc.getString(1);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    compteur = Integer.parseInt(maxid) + 1;
                    try {
                        PreparedStatement ps = condb.prepareStatement(l_Aplly);
                        ps.setString(1, valueOf(compteur));
                        ps.setString(2, "unpaid");
                        ps.setInt(3, (int) days);
                        ps.setString(4, ((TextField) sdb.getEditor()).getText());
                        ps.setString(5, ((TextField) edb.getEditor()).getText());
                        ps.setString(6, Session.id);
                        ps.setString(7, "Waiting");
                        ps.execute();
                        ps.close();


                    } catch (Exception error) {
                        System.err.println(error);
                    }
                } else {
                    textal.setText("You have reached the maximum value of your unpaid leave");
                    textal.setFill(Color.RED);
                    textal.setFont(Font.font("Bold", 18));
                }
            }

        }
        else {
            textal.setText("Please enter  information");
            textal.setFill(Color.RED);
            textal.setFont(Font.font("Bold", 18));
        }

    }

    @FXML
    public void paidleave(ActionEvent epl) throws SQLException {

        if( !sds.isEmpty() && !eds.isEmpty()){
            Statement smp = null;
            ResultSet pscmp;
            try {
                smp= condb.createStatement();
                pscmp = smp.executeQuery(maxlp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(pscmp.next()){
                if (pscmp.getInt(1)< 20) {
                    LocalDate date1= sdb.getValue();
                    LocalDate date2= edb.getValue();
                    //long diffrence = DAYS.between(sdb.getValue(), sdb.getValue());
                    long days= ChronoUnit.DAYS.between(date1,date2);
                    Connection condb = con.getConnection();
                    String maxid = null;
                    try {
                        Statement s = condb.createStatement();
                        ResultSet psc = s.executeQuery(chek_idl);
                        while (psc.next()){
                            maxid = psc.getString(1);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    compteur = Integer.parseInt(maxid)+1;

                    try {
                        PreparedStatement ps = condb.prepareStatement(l_Aplly);
                        ps.setString(1, valueOf(compteur));
                        ps.setString(2, "paid");
                        ps.setInt(3,(int) days);
                        ps.setString(4, ((TextField)sdb.getEditor()).getText());
                        ps.setString(5, ((TextField)edb.getEditor()).getText());
                        ps.setString(6, Session.id);
                        ps.setString(7, "Waiting");
                        ps.execute();
                        ps.close();

                    } catch (Exception error) {
                        System.err.println(error);
                    }
                } else {
                    textal.setText("You have reached the maximum value of your paid leave");
                    textal.setFill(Color.RED);
                    textal.setFont(Font.font("Bold", 18));
                }
            }


        }
        else {
            textal.setText("Please enter information");
            textal.setFill(Color.RED);
            textal.setFont(Font.font("Bold", 18));
        }
    }
@FXML private AnchorPane BS1 ;
    @FXML
    public void sickleave(ActionEvent esl) throws IOException {
        if (!sds.isEmpty() && !eds.isEmpty()) {
            LocalDate date1= sdb.getValue();
            LocalDate date2= edb.getValue();
            //long diffrence = DAYS.between(sdb.getValue(), sdb.getValue());
            long days= ChronoUnit.DAYS.between(date1,date2);
            Connection condb = con.getConnection();
            String maxid = null;
            try {
                Statement s = condb.createStatement();
                ResultSet psc = s.executeQuery(chek_idl);
                while (psc.next()){
                    maxid = psc.getString(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            compteur = Integer.parseInt(maxid)+1;
            try {
                PreparedStatement ps = condb.prepareStatement(l_Aplly);
                ps.setString(1, valueOf(compteur));
                ps.setString(2, "sick");
                ps.setInt(3,(int) days);
                ps.setString(4, ((TextField)sdb.getEditor()).getText());
                ps.setString(5, ((TextField)edb.getEditor()).getText());
                ps.setString(6, Session.id);
                ps.setString(7, "Waiting");
                ps.execute();
                ps.close();
            } catch (Exception error) {
                System.err.println(error);
            }
        } else {
            textal.setText("Please enter information");
            textal.setFill(Color.RED);
            textal.setFont(Font.font("Bold", 18));
        }
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("Sick_leave_files.fxml"));
        Parent root =fxml.load();
        BS1.getChildren().removeAll();
        BS1.getChildren().setAll(root);

    }
    public  String b;
    FileChooser fc=new FileChooser();
    @FXML private Text uploadt;
    @FXML private AnchorPane filew;
    public File file;

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
            b=String.valueOf(uploadt);
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
        if(uploadt.getText().isBlank()){
            uploadt.setText("failed");
        }
        String sickl="UPDATE `leave` SET `Leave_files`=? WHERE `idLeave`='"+compteur+"'";

        try {
            PreparedStatement ps = condb.prepareStatement(sickl);
            ps.setString(1,uploadt.getText());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

}


}

