package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.InventoryItem;

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

    @FXML
    void f70000(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public void woodSection(ActionEvent event) throws SQLException {
        ArrayList<InventoryItem> inventoryItems = loginModel.getProduct("Wood");
        for (int i = 0 ; i < inventoryItems.size(); i++){
            InventoryItem inventoryItem = inventoryItems.get(i);
            if(inventoryItem == null){
                System.out.println("ERROR");
            }
            System.out.println(inventoryItem.getName());
            Label name = new Label(inventoryItem.getName());
            String imageUrl = "file:///" + inventoryItem.getImageUrl();
            ImageView imageView = new ImageView(imageUrl);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            imageView.setFitWidth(90);
            System.out.println(inventoryItem.getImageUrl());
            if ( i == 0){
                itemBox1.getChildren().addAll(name,imageView);
            }
            if (i == 1){
                itemBox2.getChildren().addAll(name,imageView);
            }
            System.out.println(i);
        }


    }
}