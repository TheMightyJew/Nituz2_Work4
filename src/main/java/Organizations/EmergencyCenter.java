package Organizations;

import Users.Admins.Admin;

public class EmergencyCenter extends Organization {

    public EmergencyCenter(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Emergency Center";
    }
}
