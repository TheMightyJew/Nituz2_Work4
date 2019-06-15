package DatabaseManager.Factories;

import Updates.UserAtEvent;

public class UserAtEventFactory {
    private static UserAtEventFactory ourInstance = new UserAtEventFactory();

    public static UserAtEventFactory getInstance() {
        return ourInstance;
    }

    private UserAtEventFactory() {
    }


    public UserAtEvent Build(String username, String event_title, String in_charge_of, int user_updates) {
        // TODO: 6/15/2019
        return null;
    }
}
