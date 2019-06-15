package DatabaseManager;

import DatabaseManager.Factories.UserUpdatesFactory;
import Updates.UserUpdates;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserUpdatesTableManager extends DatabaseController{
    private static UserUpdatesTableManager ourInstance = new UserUpdatesTableManager();

    public static UserUpdatesTableManager getInstance() {
        return ourInstance;
    }

    private UserUpdatesTableManager() {
    }

    public UserUpdates getUserUpdatesForUsernameAndEventTitle(String Username, String Event_Title){
        connect();
        String sql = "SELECT ID, First_Update_ID FROM User_Updates WHERE Username = ? AND Event_Title = ?";
        UserUpdates userUpdates = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Username);
            pstmt.setString(2, Event_Title);
            ResultSet rs = pstmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                if(userUpdates != null)
                    throw new UnsupportedOperationException("Should not be here");

                int ID = rs.getInt("ID");
                int First_Update_ID = rs.getInt("First_Update_ID");

                userUpdates = UserUpdatesFactory.getInstance().Build(ID, Username, Event_Title, First_Update_ID);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return userUpdates;
    }
}
