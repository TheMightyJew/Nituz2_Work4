package Users.RegularUsers;

import Controllers.ChangePasswordController;
import Organizations.Organization;

public class SecurityForceUser extends RegularUser {

    public SecurityForceUser(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization,String degree) {
        super(changePasswordController, username, mailAddress, password, organization,degree);
    }
}
