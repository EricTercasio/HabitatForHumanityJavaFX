package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by Eric on 6/20/2017.
 */
public class CreateAccountController {

    @FXML
    private TextField usernameField;

    @FXML
    private HBox zipBox;

    @FXML
    private Text lastName;

    @FXML
    private Text city;

    @FXML
    private HBox buttonBox;

    @FXML
    private TextField emailField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private HBox phoneBox;

    @FXML
    private TextField streetField;

    @FXML
    private Text password;

    @FXML
    private HBox emailBox;

    @FXML
    private VBox leftSideHBox;

    @FXML
    private Text street;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField passwordField;

    @FXML
    private HBox firstNameBox;

    @FXML
    private HBox passwordBox;

    @FXML
    private Text state;

    @FXML
    private HBox pane;

    @FXML
    private Text email;

    @FXML
    private Text zip;

    @FXML
    private VBox rightSideHBox;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private HBox streetBox;

    @FXML
    private TextField cityField;

    @FXML
    private Text newAccountText;

    @FXML
    private ComboBox<?> stateComboBox;

    @FXML
    private HBox stateBox;

    @FXML
    private BorderPane borderPane;

    @FXML
    private HBox cityBox;

    @FXML
    private Text firstName;

    @FXML
    private TextField zipField;

    @FXML
    private Text phoneNumber;

    @FXML
    private HBox lastNameBox;

    @FXML
    private HBox usernameBox;

    @FXML
    private Text username;

    public void createAccount(ActionEvent event){


    }
    public void back(ActionEvent event) throws IOException {
        changeScene("/view/LoginView.fxml",event);

    }
    public void changeScene(String url, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}

