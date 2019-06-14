package Users.Admins;

import Controllers.ChangePasswordController;
import Controllers.CreateCategoryController;
import Organizations.Organization;
import Users.RegisteredUser;

public abstract class Admin extends RegisteredUser {

    public Admin(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization) {
        super(changePasswordController, username, mailAddress, password, organization);
    }
}
