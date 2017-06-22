package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Kitcatski on 6/21/2017.
 */
public class ManagerController implements Initializable{
    @FXML
    private ComboBox<String> sectionComboBox;

    @FXML
    private HBox descriptionBox;

    @FXML
    private HBox sectionBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField productField;

    @FXML
    private Label description;

    @FXML
    private Button logoutButton;

    @FXML
    private RadioButton addRadioButton;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField priceField;

    @FXML
    private Label price;

    @FXML
    private TextField productIDField;

    @FXML
    private HBox priceBox;

    @FXML
    private Button imageButton;

    @FXML
    private RadioButton removeRadioButton;

    @FXML
    private Label image;

    @FXML
    private HBox nameBox;

    @FXML
    private VBox removeVBox;

    @FXML
    private Button addButton;

    @FXML
    private Label managerText;

    @FXML
    private Label sectionText;

    @FXML
    private HBox radioBox;

    @FXML
    private HBox imageBox;

    @FXML
    private Label name;

    @FXML
    private Button removeButton;

    @FXML
    private Label productIDText;

    @FXML
    private VBox addVBox;

    @FXML
    private Label addRemoveText;

    private ToggleGroup toggleGroup = new ToggleGroup();

    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Wood","Metal","Plastic","Tool","Misc"
            );
    LoginModel loginModel = new LoginModel();
    String imageUrl = null;

    public void showAddVBox(ActionEvent event){
        if(removeVBox.isVisible()){
            removeVBox.setVisible(false);
        }
        addVBox.setVisible(true);
    }
    public void showRemoveVBox(ActionEvent event){
        if(addVBox.isVisible()){
            addVBox.setVisible(false);
        }
        removeVBox.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRadioButton.setToggleGroup(toggleGroup);
        removeRadioButton.setToggleGroup(toggleGroup);
        sectionComboBox.setItems(options);
    }
    public void addItem(ActionEvent event) throws SQLException {

        int productID = Integer.parseInt((productField.getText()));

        loginModel.createItem(sectionComboBox.getSelectionModel().getSelectedItem(),nameField.getText(),
                Double.valueOf(priceField.getText()),descriptionArea.getText(),productID,imageUrl);
        loginModel.restartConnection();
    }
    public void getImageUrl(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file!=null){
            imageUrl = file.getAbsolutePath();
        }
    }
}
