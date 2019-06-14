package Organizations.SecurityForces;

import Organizations.Organization;
import Users.Admins.Admin;

public abstract class SecurityForce extends Organization {

    public SecurityForce(String id, Admin admin) {
        super(id, admin);
    }
}
