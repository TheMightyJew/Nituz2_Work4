package Users.Admins;

import Controllers.ChangePasswordController;
import Organizations.Organization;

public class SecurityForceAdmin extends Admin {

    public SecurityForceAdmin(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization) {
        super(changePasswordController, username, mailAddress, password, organization);
    }
}
