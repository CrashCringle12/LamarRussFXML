package Controller;

import Model.Alertmodel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DetailModelViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="alertID"
    private Text alertID; // Value injected by FXMLLoader

    @FXML // fx:id="description"
    private TextArea description; // Value injected by FXMLLoader

    @FXML // fx:id="date"
    private Text date; // Value injected by FXMLLoader

    @FXML // fx:id="severity"
    private Text severity; // Value injected by FXMLLoader

    @FXML // fx:id="accountName"
    private Text accountName; // Value injected by FXMLLoader

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader
    
    @FXML // fx:id="button"
    private Button button; // Value injected by FXMLLoader
    Alertmodel selectedModel;
        
  public void initData(Alertmodel model) {
              System.out.println("TEst");
        selectedModel = model;
        alertID.setText(model.getId().toString());
        accountName.setText("Account: " + model.getAccountname());
        severity.setText("Severity: " + (model.getSeverity() ? "Bad"  : "Moderate"));
        date.setText("Datse: " + model.getDate().toString());
        date.setWrappingWidth(500);
        alertID.setWrappingWidth(200);
        accountName.setWrappingWidth(500);
        description.setText(model.getDescription());
        try {

            String imagename = "/resources/images/icon.png";
            Image profile = new Image(getClass().getResourceAsStream(imagename));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert alertID != null : "fx:id=\"alertID\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert severity != null : "fx:id=\"severity\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert accountName != null : "fx:id=\"accountName\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'mainmenu.fxml'.";
        
    }
}
