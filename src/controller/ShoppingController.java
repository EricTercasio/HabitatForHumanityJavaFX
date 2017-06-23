package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.InventoryItem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @FXML
    void f70000(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBoxs.add(itemBox1);
        itemBoxs.add(itemBox2);
        itemBoxs.add(itemBox3);
        itemBoxs.add(itemBox4);
        itemBoxs.add(itemBox5);
        itemBoxs.add(itemBox6);

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
    public void setItems(ArrayList<InventoryItem> inventoryItems){
        for (int i = 0 ; i < inventoryItems.size(); i++){
            InventoryItem inventoryItem = inventoryItems.get(i);
            if(inventoryItem == null){
                System.out.println("ERROR");
            }
            Label name = new Label(inventoryItem.getName());
            Hyperlink descriptionLink = new Hyperlink("Description");
            Label price = new Label("$"+String.valueOf(inventoryItem.getPrice()));
            String imageUrl = "file:///" + inventoryItem.getImageUrl();
            Button addToCartButton = new Button("Add to cart");
            ImageView imageView = new ImageView(imageUrl);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setFitWidth(90);
            if(!itemBoxs.get(i).getChildren().isEmpty()){
                itemBoxs.get(i).getChildren().clear();
            }
            itemBoxs.get(i).getChildren().addAll(name,imageView,descriptionLink,price,addToCartButton);
            itemBoxs.get(i).setAlignment(Pos.CENTER);
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
    public void clearItems(){
        for(int i = 0; i < itemBoxs.size(); i++){
            itemBoxs.get(i).getChildren().clear();
        }
    }
}