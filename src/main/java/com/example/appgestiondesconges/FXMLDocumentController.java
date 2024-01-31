package com.example.appgestiondesconges;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;


public class FXMLDocumentController implements Initializable {

    HostServices hostServices;

    FileChooser fc=new FileChooser();
    @FXML private Text uploadt;
    @FXML private AnchorPane filew;
    @FXML private Label label;
    public static  File file;
    @FXML
    public void uploadf(ActionEvent eu) {
        Window stage = filew.getScene().getWindow();
        fc.setTitle("load");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text", "*.txt", "*.doc"),
                new FileChooser.ExtensionFilter("pdf", "*.pdf"),
                new FileChooser.ExtensionFilter("img", "*.jpg", "*.png", "*.gif"));

        file = fc.showOpenDialog(stage);
        if (file != null) {
            uploadt.setText(file.getPath());
        } else {
            System.out.println("error");
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        hostServices.showDocument("C:\\Users\\Yasmina\\Downloads\\tablerecherche.txt");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fc.setInitialDirectory(new File("C:\\"));
    }

    public void setGetHostController(HostServices hostServices)
    {
        this.hostServices = hostServices;
    }
    @FXML
    protected void savefile(ActionEvent es){

    }

}
 /*if (InfoTool.osName.toLowerCase().contains("win")) {
            try {
                Runtime.getRuntime().exec("explorer.exe /select," +file.getPath() );
            } catch (IOException ex) {
                Main.logger.log(Level.WARNING, ex.getMessage(), ex);
            }
        }*/