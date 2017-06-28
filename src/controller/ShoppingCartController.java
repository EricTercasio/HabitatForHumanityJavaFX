package controller;

/**
 * Created by Kitcatski on 6/26/2017.
 */
import app.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.InventoryItem;
import model.LineItem;


import javax.sound.sampled.Line;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShoppingCartController implements Initializable{

    @FXML
    private Button removeButton9;

    @FXML
    private Button removeButton8;

    @FXML
    private Button removeButton7;

    @FXML
    private Button removeButton6;

    @FXML
    private VBox checkoutBox;

    @FXML
    private VBox infoBox4;

    @FXML
    private VBox infoBox3;

    @FXML
    private VBox infoBox2;

    @FXML
    private VBox infoBox1;

    @FXML
    private VBox infoBox8;

    @FXML
    private VBox infoBox7;

    @FXML
    private VBox infoBox6;

    @FXML
    private VBox infoBox5;

    @FXML
    private VBox infoBox9;

    @FXML
    private HBox itemBox10;

    @FXML
    private VBox infoBox10;

    @FXML
    private Label total;

    @FXML
    private HBox itemBox7;

    @FXML
    private Button backButton;

    @FXML
    private HBox itemBox6;

    @FXML
    private HBox itemBox5;

    @FXML
    private HBox itemBox4;

    @FXML
    private AnchorPane pane;

    @FXML
    private HBox itemBox3;

    @FXML
    private Label checkout;

    @FXML
    private HBox itemBox2;

    @FXML
    private HBox itemBox1;

    @FXML
    private HBox itemBox9;

    @FXML
    private Button removeButton10;

    @FXML
    private HBox itemBox8;

    @FXML
    private Label tax;

    @FXML
    private Button checkoutButton;

    @FXML
    private Label subtotal;

    @FXML
    private Button removeButton1;

    @FXML
    private Button removeButton5;

    @FXML
    private Button removeButton4;

    @FXML
    private Button removeButton3;

    @FXML
    private Button removeButton2;

    private ArrayList<HBox> itemBoxs = new ArrayList<>();

    private int userID;

    private LoginModel loginModel = new LoginModel();

    private ArrayList<Button> removeButtons = new ArrayList();

    private ArrayList<VBox> infoBoxs = new ArrayList<>();

    private ArrayList<LineItem> lineItemList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userID = Start.getUserID();
        addToBoxs();
        try {
            setItems();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void setItems() throws SQLException {
        double doubleSubTotal = 0;
        double doubleTax;
        double doubleTotal = 0;
        ArrayList<Integer> lineItemIDs = loginModel.getLineItemIDsFromCart(userID);
        loginModel.restartConnection();
        ArrayList<LineItem> lineItems = new ArrayList<>();
        for(int i = 0; i < lineItemIDs.size(); i++){
            lineItems.add(loginModel.getLineItemByID(lineItemIDs.get(i)));
            lineItemList = lineItems;
            loginModel.restartConnection();
        }
        loginModel.restartConnection();
        for(int k = 0; k < lineItems.size(); k ++){
            LineItem lineItem = lineItems.get(k);
            InventoryItem inventoryItem = loginModel.getProductByID((lineItem.getProductID()));
            loginModel.restartConnection();
            String imageUrl = "file:///" + inventoryItem.getImageUrl();
            String name = inventoryItem.getName();
            int productID = inventoryItem.getProductID();
            int quantity = lineItem.getQuantity();
            double price = inventoryItem.getPrice();
            doubleSubTotal += (price * quantity);
            ImageView imageView = new ImageView(imageUrl);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setFitWidth(90);
            //infoBoxs.get(k).setAlignment(Pos.CENTER);
            Label idLabel = new Label(String.valueOf(lineItem.getId()));
            idLabel.setVisible(false);
            infoBoxs.get(k).getChildren().add(0,new Label("Name : " +name));
            infoBoxs.get(k).getChildren().add(1,new Label("Product ID : " +String.valueOf(productID)));
            infoBoxs.get(k).getChildren().add(2,new Label("Price : $" +String.valueOf(price)));
            infoBoxs.get(k).getChildren().add(3,new Label("Quantity : "+String.valueOf(quantity)));
            infoBoxs.get(k).getChildren().add(4,idLabel);
            removeButtons.get(k).setVisible(true);
            itemBoxs.get(k).getChildren().add(0,imageView);
            itemBoxs.get(k).setAlignment(Pos.CENTER);
        }
        doubleTax = (doubleSubTotal * .08125);
        doubleTax = Math.round(doubleTax * 100) / 100;
        doubleTotal = doubleSubTotal + doubleTax;
        subtotal.setText("Subtotal : "+String.valueOf(doubleSubTotal));
        total.setText("Total : " +String.valueOf(doubleTotal));
        tax.setText("Tax : " +String.valueOf(doubleTax));

        loginModel.restartConnection();
    }
    public void removeItems(ActionEvent event) throws SQLException {
        Button button = (Button) event.getSource();
        HBox parent = (HBox) button.getParent();
        VBox vBox = (VBox) parent.getChildren().get(1);
        Label label = (Label) vBox.getChildren().get(4);
        int lineItemId = Integer.parseInt(label.getText());
        loginModel.deleteFromShoppingCart(userID,lineItemId);
        loginModel.restartConnection();
        loginModel.deleteLineItem(lineItemId);
        loginModel.restartConnection();
        resetBoxs();
        addToBoxs();
        setItems();
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void back(ActionEvent event) throws IOException {

        changeScene("/view/ShoppingView.fxml",event);
    }
    public void pay(ActionEvent event) throws IOException {
        changeScene("/view/PaymentView.fxml",event);
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
    public void addToBoxs(){
        itemBoxs.add(itemBox1);
        itemBoxs.add(itemBox2);
        itemBoxs.add(itemBox3);
        itemBoxs.add(itemBox4);
        itemBoxs.add(itemBox5);
        itemBoxs.add(itemBox6);
        itemBoxs.add(itemBox7);
        itemBoxs.add(itemBox8);
        itemBoxs.add(itemBox9);
        itemBoxs.add(itemBox10);
        removeButtons.add(removeButton1);
        removeButtons.add(removeButton2);
        removeButtons.add(removeButton3);
        removeButtons.add(removeButton4);
        removeButtons.add(removeButton5);
        removeButtons.add(removeButton6);
        removeButtons.add(removeButton7);
        removeButtons.add(removeButton8);
        removeButtons.add(removeButton9);
        removeButtons.add(removeButton10);
        infoBoxs.add(infoBox1);
        infoBoxs.add(infoBox2);
        infoBoxs.add(infoBox3);
        infoBoxs.add(infoBox4);
        infoBoxs.add(infoBox5);
        infoBoxs.add(infoBox6);
        infoBoxs.add(infoBox7);
        infoBoxs.add(infoBox8);
        infoBoxs.add(infoBox9);
        infoBoxs.add(infoBox10);
    }
    public void resetBoxs(){
        for(int i = 0; i < itemBoxs.size(); i++){
            itemBoxs.get(i).getChildren().clear();
        }
        itemBoxs.clear();
        for(int i = 0; i < infoBoxs.size(); i++){
            infoBoxs.get(i).getChildren().clear();
        }
        infoBoxs.clear();
    }
}


