package controller;
import app.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.InventoryItem;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Eric on 6/20/2017.
 */
public class ShoppingController  implements Initializable{

    @FXML
    private ImageView cartImage;

    @FXML
    private HBox accountBox;

    @FXML
    private Hyperlink myAccountText;

    @FXML
    private HBox woodBox;

    @FXML
    private HBox categoryBox;

    @FXML
    private Button metal;

    @FXML
    private HBox miscBox;

    @FXML
    private Button logoutButton;

    @FXML
    private Hyperlink cartText;

    @FXML
    private Button miscButton;

    @FXML
    private HBox metalBox;

    @FXML
    private Button woodButton;

    @FXML
    private VBox itemBox6;

    @FXML
    private Button toolsButton;

    @FXML
    private Button addToCartButton1;

    @FXML
    private Button addToCartButton2;

    @FXML
    private Button addToCartButton3;

    @FXML
    private Button addToCartButton4;

    @FXML
    private Button addToCartButton5;

    @FXML
    private Button addToCartButton6;

    @FXML
    private Hyperlink descriptionLink1;

    @FXML
    private Hyperlink descriptionLink2;

    @FXML
    private Hyperlink descriptionLink3;

    @FXML
    private Hyperlink descriptionLink4;
    @FXML
    private Hyperlink descriptionLink5;

    @FXML
    private Hyperlink descriptionLink6;


    @FXML
    private VBox itemBox5;

    @FXML
    private VBox itemBox4;

    @FXML
    private Label category;

    @FXML
    private VBox itemBox3;

    @FXML
    private HBox toolsBox;

    @FXML
    private VBox itemBox2;

    @FXML
    private Button plasticsButton;

    @FXML
    private VBox itemBox1;

    @FXML
    private HBox plasticsBox;

    private LoginModel loginModel = new LoginModel();

    private ArrayList<VBox> itemBoxs = new ArrayList<>();

    private ArrayList<Button> addToCartButtons = new ArrayList<>();

    private ArrayList<Hyperlink> descriptionLinks = new ArrayList<>();

    private int userID;
    @FXML
    void f70000(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userID = Start.getUserID();
        itemBoxs.add(itemBox1);
        itemBoxs.add(itemBox2);
        itemBoxs.add(itemBox3);
        itemBoxs.add(itemBox4);
        itemBoxs.add(itemBox5);
        itemBoxs.add(itemBox6);
        addToCartButtons.add(addToCartButton1);
        addToCartButtons.add(addToCartButton2);
        addToCartButtons.add(addToCartButton3);
        addToCartButtons.add(addToCartButton4);
        addToCartButtons.add(addToCartButton5);
        addToCartButtons.add(addToCartButton6);
        descriptionLinks.add(descriptionLink1);
        descriptionLinks.add(descriptionLink2);
        descriptionLinks.add(descriptionLink3);
        descriptionLinks.add(descriptionLink4);
        descriptionLinks.add(descriptionLink5);
        descriptionLinks.add(descriptionLink6);
    }
    public void woodSection(ActionEvent event) throws SQLException {
        clearItems();
        ArrayList<InventoryItem> inventoryItems = loginModel.getProduct("Wood");
        setItems(inventoryItems);
        loginModel.restartConnection();
    }
    public void plasticSection(ActionEvent event) throws SQLException {
        clearItems();
        ArrayList<InventoryItem> inventoryItems = loginModel.getProduct("Plastic");
        setItems(inventoryItems);
        loginModel.restartConnection();
    }
    public void toolSection(ActionEvent event) throws SQLException {
        clearItems();
        ArrayList<InventoryItem> inventoryItems = loginModel.getProduct("Tool");
        setItems(inventoryItems);
        loginModel.restartConnection();
    }
    public void metalSection(ActionEvent event) throws SQLException {
        clearItems();
        ArrayList<InventoryItem> inventoryItems = loginModel.getProduct("Metal");
        setItems(inventoryItems);
        loginModel.restartConnection();
    }
    public void miscSection(ActionEvent event) throws SQLException {
        clearItems();
        ArrayList<InventoryItem> inventoryItems = loginModel.getProduct("Misc");
        setItems(inventoryItems);
        loginModel.restartConnection();
    }

    public void addToCart(ActionEvent event) throws SQLException {
        loginModel.restartConnection();
        Button button = (Button) event.getSource();
        VBox parent = (VBox) button.getParent();
        Label label = (Label) parent.getChildren().get(0);
        Label priceLabel = (Label) parent.getChildren().get(4);
        String id = label.getText();
        String productID = id.substring(5);
        String price = priceLabel.getText().substring(1);
        double priceOfItem = Double.valueOf(price);

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setContentText("Enter the quantity");
        inputDialog.setHeaderText("Quantity");
        Optional<String> value = inputDialog.showAndWait();
        if (!value.get().isEmpty() && value.get().matches("-?\\d+(\\.\\d+)?") && Integer.valueOf(value.get()) > 0) {
            System.out.println(productID + value.get());
            int lineItemID = loginModel.createLineItem(Integer.parseInt(productID), Integer.parseInt(value.get()));
            loginModel.restartConnection();
                loginModel.restartConnection();
                loginModel.addToShoppingCart(userID, lineItemID);
                loginModel.restartConnection();
                double currentPrice = loginModel.getTotalByUserID(userID);
                double priceAndQuantity = priceOfItem * Double.valueOf(value.get());
                double total = currentPrice + priceAndQuantity;
                loginModel.restartConnection();
                loginModel.setCartTotal(userID,total);

        } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Not the correct format");
        alert.showAndWait();
    }
        loginModel.restartConnection();
    }
    public void openDescription(ActionEvent event) throws SQLException {
         Alert dialog = new Alert(Alert.AlertType.INFORMATION);
         Hyperlink hyperlink = (Hyperlink) event.getSource();
         VBox parent = (VBox) hyperlink.getParent();
         Label productIDLabel = (Label) parent.getChildren().get(0);
         String productID = productIDLabel.getText().substring(5);
         String description = loginModel.getDescriptionByID(productID);
         dialog.setHeaderText("Description");
         dialog.setContentText(description);
         dialog.showAndWait();
         loginModel.restartConnection();
    }

    public void setItems(ArrayList<InventoryItem> inventoryItems){
        for (int i = 0 ; i < inventoryItems.size(); i++) {
            InventoryItem inventoryItem = inventoryItems.get(i);
            if (inventoryItem == null) {
                System.out.println("ERROR");
            } else {
                Label productID = new Label("ID : "+String.valueOf(inventoryItem.getProductID()));
                Label name = new Label(inventoryItem.getName());
                Hyperlink descriptionLink = descriptionLinks.get(i);
                descriptionLink.setVisible(true);
                Label price = new Label("$" + String.valueOf(inventoryItem.getPrice()));
                String imageUrl = "file:///" + inventoryItem.getImageUrl();
                ImageView imageView = new ImageView(imageUrl);
                Button button = addToCartButtons.get(i);
                button.setVisible(true);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setCache(true);
                imageView.setFitWidth(90);
                if (!itemBoxs.get(i).getChildren().isEmpty()) {
                    itemBoxs.get(i).getChildren().clear();
                }
                itemBoxs.get(i).getChildren().addAll(productID,name, imageView, descriptionLink, price,button);
                itemBoxs.get(i).setAlignment(Pos.CENTER);
            }
        }

    }
    public void logout(ActionEvent event) throws IOException {

        changeScene("/view/LoginView.fxml", event);
    }
    public void goToCart(ActionEvent event) throws IOException {
        changeScene("/view/ShoppingCartView.fxml",event);
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
    public void clearItems(){
        for(int i = 0; i < itemBoxs.size(); i++){
            itemBoxs.get(i).getChildren().clear();
        }
    }

    public void setUserID(int userID) {
        this.userID = userID;

    }
}