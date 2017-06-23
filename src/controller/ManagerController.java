package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
    private Label productID;

    @FXML
    private HBox sectionBox;

    @FXML
    private TextField nameField;

    @FXML
    private Label description;

    @FXML
    private Button logoutButton;

    @FXML
    private HBox productIDBox;

    @FXML
    private HBox quantityBox;

    @FXML
    private RadioButton addRadioButton;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField priceField;

    @FXML
    private Label price;

    @FXML
    private TextField productField;

    @FXML
    private TextField productIDField;

    @FXML
    private HBox priceBox;

    @FXML
    private Button imageButton;

    @FXML
    private TextField quantityField;

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
    private Label quantityLabel;

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
                Double.valueOf(priceField.getText()),descriptionArea.getText(),productID,imageUrl,Integer.valueOf(quantityField.getText()));
        loginModel.restartConnection();
    }
    public void getImageUrl(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file!=null){
            imageUrl = file.getAbsolutePath();
        }
    }
    public void logout(ActionEvent event) throws IOException {
        changeScene("/view/LoginView.fxml", event);
    }
    public void changeScene(String url, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.close();
        appStage.setScene(scene);
        appStage.centerOnScreen();
        appStage.show();
    }
}
