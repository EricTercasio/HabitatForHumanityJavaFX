package controller;

/**
 * Created by Kitcatski on 6/27/2017.
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PaymentController {

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


}

