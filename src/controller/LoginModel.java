package controller;

import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public boolean isLogin(String user, String pass){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from Customer where username=? and password=?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
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
    public void restartConnection(){
        connection = SqliteConnection.Connector();
        if (connection == null){
            System.out.println("Connection not successfull");
            System.exit(1);
        }
    }

}
