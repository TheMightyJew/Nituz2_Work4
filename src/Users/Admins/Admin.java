package Users.Admins;

import Users.RegisteredUser;

public abstract class Admin extends RegisteredUser {
    public Admin(String username, String mailAddress, String password) {
        super(username, mailAddress, password);
    }
}
