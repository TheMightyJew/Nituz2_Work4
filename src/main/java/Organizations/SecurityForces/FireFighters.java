package Organizations.SecurityForces;

import Users.Admins.Admin;

public class FireFighters extends SecurityForce {

    public FireFighters(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return super.toString()+" Fire Fighters";
    }
}
