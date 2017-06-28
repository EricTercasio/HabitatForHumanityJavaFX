package controller;

/**
 * Created by Eric on 6/28/2017.
 */
import app.Start;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Invoice;
import model.Person;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MyAccountController implements Initializable{

    @FXML
    private Label headerText;

    @FXML
    private Text invoiceText;

    @FXML
    private Label streetLabel;

    @FXML
    private Label zipLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextArea invoiceArea;

    @FXML
    private Label accountInfoText;

    @FXML
    private ComboBox<String> invoiceComboBox;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label idLabel;

    @FXML
    private ComboBox<String> updateComboBox;

    @FXML
    private Button backButton;

    @FXML
    private Label stateLabel;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label updateInfoText;

    @FXML
    private Label emailLabel;

    @FXML
    private Label nameLabel;

    private ObservableList<String> updateOptions =
            FXCollections.observableArrayList(
                    "FirstName", "LastName", "Username", "Password", "PhoneNumber", "Street", "City","State","Zip","Email"
            );
    private ObservableList<String> invoiceOptions = FXCollections.observableArrayList();
    private int userID;
    private Person person;
    LoginModel loginModel = new LoginModel();

    ArrayList<Invoice> invoices = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userID = Start.getUserID();
        updateComboBox.setItems(updateOptions);
        try {
            person = loginModel.getPersonByID(userID);
            loginModel.restartConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idLabel.setText("ID : "+person.getId());
        nameLabel.setText("Name : " + person.getFirstName() + " " +person.getLastName());
        usernameLabel.setText("Username : "+person.getUsername());
        phoneLabel.setText("Phone Number : "+person.getPhoneNumber());
        streetLabel.setText("Street : " +person.getStreet());
        cityLabel.setText("City : "+person.getCity());
        stateLabel.setText("State : "+person.getState());
        zipLabel.setText("Zip : "+person.getZip());
        emailLabel.setText("Email : "+person.getEmail());

        try {
            invoices = loginModel.getInvoicesByUserID(userID);
            loginModel.restartConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < invoices.size(); i++){
            System.out.println(invoices.get(i).getInvoiceID());
            invoiceOptions.add("ID : " +invoices.get(i).getInvoiceID());
        }
        invoiceComboBox.setItems(invoiceOptions);


    }
    public void updateInfo(ActionEvent event) throws SQLException, IOException {
        String section = updateComboBox.getSelectionModel().getSelectedItem();
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Enter new information");
        Optional<String> result = textInputDialog.showAndWait();
        String newInfo = result.get();
        loginModel.updateAccountInfo(userID,section,newInfo);
        loginModel.restartConnection();
        changeScene("/view/MyAccountView.fxml",event);
    }

    public void getInvoice(ActionEvent event) throws SQLException {
        String id = invoiceComboBox.getSelectionModel().getSelectedItem();
        id = id.substring(5);
        int invoiceID = Integer.valueOf(id);
        Invoice invoice = loginModel.getInvoiceByInvoiceID(invoiceID);
        loginModel.restartConnection();
        invoiceArea.setText(invoice.getItems() +"Total : $" +invoice.getTotalAmount());
        System.out.println(invoice.getItems());
        System.out.println("okay");
    }

    public void back(ActionEvent event) throws IOException {
        changeScene("/view/ShoppingView.fxml",event);
    }

    public void changeScene(String url, javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.close();
        appStage.setScene(scene);
        appStage.centerOnScreen();
        appStage.show();
    }
}
