package DatabaseManager;

import Users.RegisteredUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisteredUserTableManager extends DatabaseController{
    private static RegisteredUserTableManager ourInstance = new RegisteredUserTableManager();

    public static RegisteredUserTableManager getInstance() {
        return ourInstance;
    }

    private RegisteredUserTableManager() {
    }

    public void ChangePassword(String username, String newPassword) {
        connect();
        String sql = "UPDATE Registered_Users SET Password = ? WHERE Username = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            pstmt.setString(1, username);
            pstmt.setString(2, newPassword);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();
    }

    public boolean CheckIfUsernameIsTaken(String Username){
        connect();
        String sql = "SELECT COUNT(Username) AS Amount FROM Registered_Users WHERE Username = ?";
        boolean taken = false;
        boolean alreadyEntered = false;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            pstmt.setString(1, Username);
            ResultSet rs = pstmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                if(alreadyEntered)
                    throw new UnsupportedOperationException("Should not be here");
                alreadyEntered = true;

                int Amount = rs.getInt("Amount");
                taken = Amount > 0;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();

        return taken;
    }

    public String GetPasswordByUsername(String Username){
        connect();
        String sql = "SELECT Password FROM Registered_Users WHERE Username = ?";
        String Password = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            pstmt.setString(1, Username);
            ResultSet rs = pstmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                if(Password != null)
                    throw new UnsupportedOperationException("Should not be here");

                Password = rs.getString("Password");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();

        return Password;
    }
}
