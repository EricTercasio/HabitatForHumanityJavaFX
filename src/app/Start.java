package app;

import controller.SqliteConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
    private static int userID;
    @Override
    public void start(Stage primaryStage) throws Exception{
        SqliteConnection.Connector();
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public static int getUserID(){
    return userID;
    }

    public static void setUserID(int userID) {
        Start.userID = userID;
    }
}
