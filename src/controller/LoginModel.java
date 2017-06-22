package controller;

import model.InventoryItem;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Eric on 6/20/2017.
 */
public class LoginModel {
    Connection connection = null;

    public LoginModel(){
        connection = SqliteConnection.Connector();
        if (connection == null){
            System.out.println("Connection not successfull");
            System.exit(1);
        }
    }
    public boolean isDbConnected(){
        try{
            return !connection.isClosed();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<InventoryItem> getProduct(String type) throws SQLException {
        int i = 0;
        ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
        int productID;
        String name;
        double price;
        String description;
        String imageUrl;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * from Product where Type = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,type);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            productID = resultSet.getInt(1);
            type = resultSet.getString(2);
            name = resultSet.getString(3);
            price = resultSet.getDouble(4);
            description = resultSet.getString(5);
            imageUrl = resultSet.getString(6);
            InventoryItem inventoryItem = new InventoryItem(productID,type,name,price,description,imageUrl);
            inventoryItems.add(inventoryItem);
        }
        preparedStatement.close();
        resultSet.close();
        connection.close();
        return inventoryItems;
    }
    public String isLogin(String user, String pass){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from People where username=? and password=?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            String position = resultSet.getString(13);
            if(resultSet.next()){
                System.out.println(position);
                return position;
            }else{
                return null;
            }
        }catch(SQLException e){
            return null;
        }finally{
            try{
                preparedStatement.close();
                resultSet.close();
                connection.close();
            }catch(SQLException e){
                return null;
            }
        }
    }
    public void createCustomer(String firstName,String lastName,String username,String password,String phoneNumber,
    String street, String city,String state, String zip, String email, int cartID, String position) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO People (FirstName,LastName,Username,Password,PhoneNumber,Street,City,State,Zip,Email,CartID,Position) VALUES("
                + "'" + firstName + "', " + "'" + lastName + "', "+ "'" + username + "', " + "'" + password + "', "
                + "'" + phoneNumber + "', "+ "'" + street + "', "+ "'" + city + "', "+ "'" + state + "', " + "'" + zip + "', "+ "'" + email + "', "
                + "'" + cartID + "', "+ "'" + position + "'); ";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public void createItem(String type, String name, double price, String description, Integer ID, String imageURL) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO Product"  +"(ProductID,Type,Name,Price,Description,Image) VALUES("
                +"'" + ID + "', " +"'" + type + "', " + "'" + name + "', "+ "'" + price + "', " + "'" + description + "', "+ "'" + imageURL+"')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public void restartConnection(){
        connection = SqliteConnection.Connector();
        if (connection == null){
            System.out.println("Connection not successfull");
            System.exit(1);
        }
    }

}
