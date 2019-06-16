package DatabaseManager.Factories;

import DatabaseManager.RegularUsersTableManager;
import Updates.UserAtEvent;
import Users.RegularUsers.RegularUser;

public class UserAtEventFactory {
    private static UserAtEventFactory ourInstance = new UserAtEventFactory();

    public static UserAtEventFactory getInstance() {
        return ourInstance;
    }

    private UserAtEventFactory() {
    }


    public UserAtEvent Build(String username, String event_title, int user_updates) {
        RegularUser user = RegularUsersTableManager.getInstance().getUserByUsername(username);
        UserAtEvent userAtEvent = new UserAtEvent(user);
        return userAtEvent;
    }
}
