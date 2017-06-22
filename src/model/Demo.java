package model;

import controller.LoginModel;
import controller.SqliteConnection;

import java.sql.SQLException;

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
        loginModel.getProduct("Wood");

    }
}
