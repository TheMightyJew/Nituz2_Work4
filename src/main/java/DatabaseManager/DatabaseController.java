package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseController {
    private String databasePath;
    protected static Connection conn = null;
    protected static int amountOfConnected = 0;

    public DatabaseController() {
        this.databasePath = "jdbc:sqlite:src\\main\\resources\\Emer_Agency.db";
    }

    protected void connect(){
        if(amountOfConnected > 0) { //if there is already someone inside, just move on...
            amountOfConnected++;
            return;
        }

        conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(databasePath);

            System.out.println("Connection to SQLite has been established.");
            amountOfConnected++;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void disconnect(){
        try {
            amountOfConnected--;
            if(amountOfConnected > 0)
                return;

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
