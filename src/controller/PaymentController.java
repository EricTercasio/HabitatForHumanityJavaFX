package controller;

/**
 * Created by Kitcatski on 6/27/2017.
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.InventoryItem;
import model.LineItem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentController implements Initializable{

    @FXML
    private Label amountToPay;

    @FXML
    private ComboBox<String> expirationYear;

    @FXML
    private Button submitButton;

    @FXML
    private Label amount;

    @FXML
    private Label headerText;

    @FXML
    private Label invoiceText;

    @FXML
    private ComboBox<String> cardComboBox;

    @FXML
    private Label securityCode;

    @FXML
    private TextField nameField;

    @FXML
    private Label creditCardType;

    @FXML
    private ComboBox<String> expirationMonth;

    @FXML
    private TextField cardField1;

    @FXML
    private Label nameOnCard;

    @FXML
    private ImageView imageViewLeft;

    @FXML
    private Button backButton;

    @FXML
    private Label invoiceNumber;

    @FXML
    private Label expiration;

    @FXML
    private TextField securityField;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField cardField2;

    @FXML
    private TextField cardField3;

    @FXML
    private Label cardNumber;

    @FXML
    private ImageView imageViewRight;

    @FXML
    private TextField cardField4;

    private LoginModel loginModel = new LoginModel();

    private int userID;

    ObservableList<String> cardOptions =
            FXCollections.observableArrayList(
                    "Visa","MasterCard"
            );
    ObservableList<String> monthOptions =
            FXCollections.observableArrayList(
                    "01","02","03","04","05","06","07","08","09","10","11","12"
            );
    ObservableList<String> yearOptions =
            FXCollections.observableArrayList(
                    "2017","2018","2019","2020","2021","2022","2023"
            );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userID = Start.getUserID();
        try {
            double price = loginModel.getTotalByUserID(userID);
            loginModel.restartConnection();
            amount.setText(String.valueOf(price));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cardComboBox.setItems(cardOptions);
        expirationMonth.setItems(monthOptions);
        expirationYear.setItems(yearOptions);
    }
    public void back(ActionEvent event) throws IOException {
        changeScene("/view/ShoppingCartView.fxml",event);
    }
    public void pay(ActionEvent event) throws SQLException, IOException {
        ArrayList<Integer> lineItemIDs = loginModel.getLineItemIDsFromCart(userID);
        loginModel.restartConnection();
        String items = "";
        for(int i = 0; i < lineItemIDs.size(); i++) {
            LineItem tempLineItem = loginModel.getLineItemByID(lineItemIDs.get(i));
            loginModel.restartConnection();
            InventoryItem tempItem = loginModel.getProductByID(tempLineItem.getProductID());
            loginModel.restartConnection();
            items += "Item # " + i + " ID : " + tempItem.getProductID() + " | Name : " + tempItem.getName() + " | Price : " + tempItem.getPrice() + " | Quantity : "+tempLineItem.getQuantity() + "\n";
        }
        System.out.println(items);
        loginModel.restartConnection();
        double price = loginModel.getTotalByUserID(userID);
        price = price + (price * .08125);
        loginModel.restartConnection();
        loginModel.updateInvoiceByID(userID,price,items,true);
        loginModel.restartConnection();
        loginModel.createInvoice(userID);
        loginModel.restartConnection();
        for(int i = 0; i < lineItemIDs.size(); i++){
            loginModel.deleteLineItem(lineItemIDs.get(i));
            loginModel.restartConnection();
        }
        loginModel.clearShoppingCart(userID);
        loginModel.restartConnection();
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

