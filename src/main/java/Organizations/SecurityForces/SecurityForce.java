package Organizations.SecurityForces;

import Organizations.Organization;
import Users.Admins.Admin;

public abstract class SecurityForce extends Organization {

    public SecurityForce(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Security Force";
    }
}
