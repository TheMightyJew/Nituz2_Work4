package DatabaseManager.Factories;

import Updates.UserUpdates;

public class UserUpdatesFactory {
    private static UserUpdatesFactory ourInstance = new UserUpdatesFactory();

    public static UserUpdatesFactory getInstance() {
        return ourInstance;
    }

    private UserUpdatesFactory() {
    }

    public UserUpdates Build(int id, String username, String event_title, int first_update_id) {
        // TODO: 6/15/2019
        return null;
    }
}
