package Users.RegularUsers;

import Users.RegisteredUser;

public abstract class RegularUser extends RegisteredUser {
    public RegularUser(String username, String mailAddress, String password) {
        super(username, mailAddress, password);
    }
}
