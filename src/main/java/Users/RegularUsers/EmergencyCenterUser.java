package Users.RegularUsers;

import Controllers.ChangePasswordController;
import Organizations.Organization;

public class EmergencyCenterUser extends RegularUser {

    public EmergencyCenterUser(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization) {
        super(changePasswordController, username, mailAddress, password, organization);
    }
}
