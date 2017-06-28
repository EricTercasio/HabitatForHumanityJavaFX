package controller;

import model.InventoryItem;
import model.LineItem;

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
        int quantity;
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
            quantity = resultSet.getInt(7);
            InventoryItem inventoryItem = new InventoryItem(productID,type,name,price,description,imageUrl,quantity);
            inventoryItems.add(inventoryItem);
        }
        preparedStatement.close();
        resultSet.close();
        connection.close();
        return inventoryItems;
    }

    public double getPriceByProductID(int productID) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "select * from Product where ProductID =?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,productID);
        resultSet = preparedStatement.executeQuery();
        double price = resultSet.getDouble(3);
        preparedStatement.close();
        resultSet.close();
        connection.close();
        return price;
    }
    public void setCartTotal(int userID, double amount) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "UPDATE ShoppingCart SET TotalPrice=? WHERE UserID=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1,amount);
        preparedStatement.setInt(2,userID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public double getTotalByUserID(int userID) throws SQLException {
        double price;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT TotalPrice FROM ShoppingCart WHERE UserID=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,userID);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        price = resultSet.getDouble(1);
        preparedStatement.close();
        resultSet.close();
        connection.close();
        return price;
    }
    public boolean findUserShoppingCart(int userID){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from ShoppingCart where UserID=?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            return false;
        }finally{
            try{
                preparedStatement.close();
                resultSet.close();
                connection.close();
            }catch(SQLException e){
                return false;
            }
        }
    }
    public ArrayList isLogin(String user, String pass){
        ArrayList arrayList = new ArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from People where username=? and password=?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            String position = resultSet.getString(13);
            int userID = resultSet.getInt(1);
            arrayList.add(position);
            arrayList.add(userID);
            if(resultSet.next()){
                return arrayList;
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
    public void deleteLineItem(int lineItemID) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "DELETE FROM LineItem WHERE ID=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,lineItemID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public void deleteFromShoppingCart(int userID, int lineItemID) throws SQLException {
        int counter = 1;
        int column = 4;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM ShoppingCart WHERE UserID=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,userID);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        for(column = 4; column <  13; column++){
            if(resultSet.getInt(column) == lineItemID){
                break;
            }
            counter++;
        }
        String replaceQuery = "UPDATE ShoppingCart SET LineItemID" +counter + "=? WHERE UserID=?";
        preparedStatement = connection.prepareStatement(replaceQuery);
        preparedStatement.setInt(1,0);
        preparedStatement.setInt(2,userID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        resultSet.close();
        connection.close();

    }
    public void createCustomer(String firstName,String lastName,String username,String password,String phoneNumber,
    String street, String city,String state, String zip, String email, int cartID, String position) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO People (FirstName,LastName,Username,Password,PhoneNumber,Street,City,State,Zip,Email,CartID,Position) VALUES("
                + "'" + firstName + "', " + "'" + lastName + "', "+ "'" + username + "', " + "'" + password + "', "
                + "'" + phoneNumber + "', "+ "'" + street + "', "+ "'" + city + "', "+ "'" + state + "', " + "'" + zip + "', "+ "'" + email + "', "
                + "'" + "?" + "', "+ "'" + position + "'); ";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public void setUserCart(int userID, int cartID) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "UPDATE People SET CartID=? WHERE ID=? ";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,cartID);
        preparedStatement.setInt(2,userID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void createItem(String type, String name, double price, String description, Integer ID, String imageURL, int quantity) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO Product"  +"(ProductID,Type,Name,Price,Description,Image,Quantity) VALUES("
                +"'" + ID + "', " +"'" + type + "', " + "'" + name + "', "+ "'" + price + "', " + "'" + description + "', "+ "'" + imageURL + "', "+ "'" + quantity+"')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    public String getDescriptionByID(String id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from Product where ProductID=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            String description = resultSet.getString(5);
            if (resultSet.next()) {
                return description;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                return null;
            }
        }
    }
    public int createLineItem(int productID, int quantity) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO LineItem (ProductID,Quantity) VALUES("
                +"'" + productID + "', " +"'" + quantity + "');";
        String findQuery = "SELECT last_insert_rowid()";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(findQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        int lineItemID = resultSet.getInt(1);
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return lineItemID;
    }

    public int createShoppingCart(int userID) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO ShoppingCart"  +"(UserID,InvoiceID,LineItemID1,LineItemID2,LineItemID3,LineItemID4,LineItemID5,LineItemID6,LineItemID7,LineItemID8,LineItemID9,LineItemID10,TotalPrice) VALUES("
                +"'" + userID + "', "+"'" + "?" + "', "+"'" + 0 + "', "+"'" + 0 + "', "+"'" + 0 + "', "+"'" + 0 + "', "+"'" + 0 + "', "+"'" + 0 + "', "
                +"'" + 0 + "', "+"'" + 0 + "', "+"'" + 0 + "', "+"'" + 0 + "', "+"'" + 0 + "');";
        String findQuery = "SELECT last_insert_rowid()";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(findQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        int cartID = resultSet.getInt(1);
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return cartID;

    }
    public void addToShoppingCart(int userID,int lineItemID) throws SQLException {

        int counter = 1;
        int column = 4;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        String findQuery = "select * from ShoppingCart where UserID=?";
        preparedStatement = connection.prepareStatement(findQuery);
        preparedStatement.setInt(1,userID);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
            while (resultSet.getInt(column) != 0) {
                column++;
                counter++;
            }

        resultSet.close();
        String query = "UPDATE ShoppingCart SET LineItemID" +counter + "=? WHERE UserID=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,lineItemID);
        preparedStatement.setInt(2,userID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }
    public ArrayList<Integer> getLineItemIDsFromCart(int userID) throws SQLException {
        ArrayList<Integer> result = new ArrayList();
        int counter = 1;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT LineItemID1,LineItemID2,LineItemID3,LineItemID4,LineItemID5,LineItemID6,LineItemID7,LineItemID8,LineItemID9,LineItemID10 FROM ShoppingCart WHERE UserID=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,userID);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        for(counter = 1; counter < 11;){
            if(resultSet.getInt(counter) != 0){
                result.add(resultSet.getInt(counter));
                counter++;
            }else{
                counter++;
            }
        }
//             (resultSet.getInt(counter) != 0) {
//                if (counter == 13) {
//                    break;
//                }
//                result.add(resultSet.getInt(counter));
//                counter++;
//            }
//        }
        preparedStatement.close();
        resultSet.close();
        connection.close();
        return result;
    }
    public LineItem getLineItemByID(int lineItemID) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM LineItem WHERE ID=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,lineItemID);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        LineItem lineItem = new LineItem(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3));
        preparedStatement.close();
        resultSet.close();
        connection.close();
        return lineItem;
    }
    public InventoryItem getProductByID(int productID) throws SQLException {
        InventoryItem inventoryItem = new InventoryItem(0,null,null,0,null,null,0);
        int id;
        String type;
        String name;
        double price;
        String description;
        String image;
        int quantity;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM Product WHERE ProductID=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,productID);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            id = resultSet.getInt(1);
            type = resultSet.getString(2);
            name = resultSet.getString(3);
            price = resultSet.getDouble(4);
            description = resultSet.getString(5);
            image = resultSet.getString(6);
            quantity = resultSet.getInt(7);
            inventoryItem = new InventoryItem(id,type,name,price,description,image,quantity);
        }
        preparedStatement.close();
        resultSet.close();
        connection.close();
        return inventoryItem;
    }

    public void restartConnection(){
        connection = SqliteConnection.Connector();
        if (connection == null){
            System.out.println("Connection not successfull");
            System.exit(1);
        }
    }

}
