package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private DBConnection() {

    }

    public static final String URL = "jdbc:postgresql://localhost:5432/Store";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "mehmet619";

    static{
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found.", e);
        }
    }

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL,USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not connect.", e);
        }
    }
}
