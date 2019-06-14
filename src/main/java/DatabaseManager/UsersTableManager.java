package DatabaseManager;

import Users.RegisteredUser;

import java.util.List;

public class UsersTableManager extends DatabaseController {
    private static UsersTableManager ourInstance = new UsersTableManager();

    public static UsersTableManager getInstance() {
        return ourInstance;
    }

    private UsersTableManager() {
    }

    public List<RegisteredUser> getAllUsers(){

    }
}
