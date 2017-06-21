package controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Eric on 6/20/2017.
 */
public class SqliteConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:HabitatForHumanityDB.sqlite");
            return connection;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
