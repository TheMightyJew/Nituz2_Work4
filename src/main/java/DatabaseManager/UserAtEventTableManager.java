package DatabaseManager;

import DatabaseManager.Factories.UserAtEventFactory;
import Updates.UserAtEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAtEventTableManager extends DatabaseController {
    private static UserAtEventTableManager ourInstance = new UserAtEventTableManager();

    public static UserAtEventTableManager getInstance() {
        return ourInstance;
    }

    private UserAtEventTableManager() {
    }

    public List<UserAtEvent> getUserAtEventForUsername(String Username){
        connect();
        String sql = "SELECT Event_Title, User_Updates FROM User_At_Event WHERE Regular_User_Username = ?";
        List<UserAtEvent> userAtEvents = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String Event_Title = rs.getString("Event_Title");
                int User_Updates = rs.getInt("User_Updates");

                UserAtEvent curr = UserAtEventFactory.getInstance().Build(Username, Event_Title, User_Updates);
                userAtEvents.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return userAtEvents;
    }

    public void CreateUserAtEvent(UserAtEvent userAtEvent){
        connect();
        String sql = "INSERT INTO User_At_Event (Regular_User_Username, Event_Title, User_Updates) VALUES (?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userAtEvent.getUser().getUsername());
            pstmt.setString(2, userAtEvent.getEvent().getTitle());
            pstmt.setInt(3, userAtEvent.getUpdates().getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();
    }

    public List<UserAtEvent> getUserAtEventForEvent(String title) {
        connect();
        String sql = "SELECT Regular_User_Username, User_Updates FROM User_At_Event WHERE Event_Title = ?";
        List<UserAtEvent> userAtEvents = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                String Username = rs.getString("Regular_User_Username");
                int User_Updates = rs.getInt("User_Updates");

                UserAtEvent curr = UserAtEventFactory.getInstance().Build(Username, title, User_Updates);
                userAtEvents.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return userAtEvents;
    }
}
