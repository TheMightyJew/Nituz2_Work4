package DatabaseManager;

import DatabaseManager.Factories.UserAtEventFactory;
import Updates.UserAtEvent;

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
        String sql = "SELECT Event_Title, In_Charge_Of, User_Updates FROM User_At_Event WHERE Regular_User_Username = ?";
        List<UserAtEvent> userAtEvents = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String Event_Title = rs.getString("Event_Title");
                String In_Charge_Of = rs.getString("In_Charge_Of");
                int User_Updates = rs.getInt("User_Updates");

                UserAtEvent curr = UserAtEventFactory.getInstance().Build(Username, Event_Title, In_Charge_Of, User_Updates);
                userAtEvents.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return userAtEvents;
    }
}
