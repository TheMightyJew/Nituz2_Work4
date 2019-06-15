package Users.RegularUsers;

import Controllers.ChangePasswordController;
import Organizations.Organization;
import Users.RegisteredUser;

public abstract class RegularUser extends RegisteredUser {

    public RegularUser(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization,String degree) {
        super(changePasswordController, username, mailAddress, password, organization , degree);
    }
}
