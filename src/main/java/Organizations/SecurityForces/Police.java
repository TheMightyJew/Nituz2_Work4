package Organizations.SecurityForces;

import Users.Admins.Admin;

public class Police extends SecurityForce {

    public Police(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return super.toString()+" Police";
    }
}
