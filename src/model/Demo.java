package model;

import controller.LoginModel;
import controller.SqliteConnection;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Kitcatski on 6/21/2017.
 */
public class Demo {
    public static void main(String[] args) throws SQLException {
        //Metal metal = new Metal("Iron",20,"Type of metal");
        //Metal cooper = new Metal("Copper",15,"Type of metal");
        //System.out.println(metal.getProductID());
        SqliteConnection.Connector();
        LoginModel loginModel = new LoginModel();
        ArrayList<Integer> lineItemIDs = loginModel.getLineItemIDsFromCart(9);
        for(int i =0; i < lineItemIDs.size();i++){
            System.out.println(lineItemIDs.get(i));
        }
        loginModel.restartConnection();
        //System.out.println(loginModel.getLineItemByID(20).toString());
        loginModel.getProductByID(9928);

    }
}
