package DatabaseManager;

import Users.RegisteredUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisteredUserTableManager extends DatabaseController{
    private static RegisteredUserTableManager ourInstance = new RegisteredUserTableManager();

    public static RegisteredUserTableManager getInstance() {
        return ourInstance;
    }

    private RegisteredUserTableManager() {
    }

    public void ChangePassword(RegisteredUser user, String newPassword) {
        connect();
        String sql = "UPDATE Registered_Users SET Password = ? WHERE Username = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, newPassword);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();
    }
}
