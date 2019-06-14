package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseController {
    private String databasePath;
    protected Connection conn;

    public DatabaseController() {
        this.databasePath = "jdbc:sqlite:src\\main\\resources\\Emer_Agency.db";
        conn = null;
    }

    protected void connect(){
        conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(databasePath);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void disconnect(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
