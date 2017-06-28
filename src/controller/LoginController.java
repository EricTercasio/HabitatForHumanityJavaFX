package controller;
import app.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by Eric on 6/20/2017.
 */
public class LoginController implements Initializable{

    @FXML
    private TextField usernameField;

    @FXML
    private HBox buttonBox;

    @FXML
    private Label connectedText;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Text password;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView logo;

    @FXML
    private PasswordField passwordField;

    @FXML
    private HBox passwordBox;

    @FXML
    private VBox pane;

    @FXML
    private HBox usernameBox;

    @FXML
    private Text username;

    @FXML
    private Button guestButton;

    LoginModel loginModel = new LoginModel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(loginModel.isDbConnected()){
            connectedText.setText("Connected to database");
        }else{
            connectedText.setText("Not connected to database");
        }

    }
    public void login(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        ArrayList arrayList = loginModel.isLogin(username,password);


        if(arrayList != null){
            String position = (String) arrayList.get(0);
            int userID = (int) arrayList.get(1);
            if(position.equals("Customer")) {
                Start.setUserID(userID);
                System.out.println(Start.getUserID());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShoppingView.fxml"));
                Parent root = loader.load();
                ShoppingController shoppingController;
                shoppingController = loader.getController();
                shoppingController.setUserID(userID);
                Scene newScene = new Scene(root);
                Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                newStage.close();
                newStage.setScene(newScene);
                newStage.centerOnScreen();
                newStage.show();

            }
            else if(position.equals("Manager")){
                changeScene("/view/ManagerView.fxml",event);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username/Password are incorrect.");
            alert.showAndWait();
            SqliteConnection.Connector();
            loginModel.restartConnection();
        }

    }
    public void createAccount(ActionEvent event) throws IOException {
        changeScene("/view/CreateAccountView.fxml",event);
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
