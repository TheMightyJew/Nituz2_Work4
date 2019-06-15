package Organizations.SecurityForces;

import Users.Admins.Admin;

public class RedCross extends SecurityForce {

    public RedCross(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return super.toString()+" Red Cross";
    }
}
