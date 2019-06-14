package Organizations;

import Organizations.SecurityForces.SecurityForce;
import Users.Admins.Admin;
import Users.RegularUsers.RegularUser;

import java.util.ArrayList;
import java.util.List;

public abstract class Organization {

    protected String id;
    protected Admin admin;
    protected List<RegularUser> users;
    protected List<OrganizationAtEvent> events;

    public Organization(String id) {
        this.id = id;
        users = new ArrayList<>();
        events = new ArrayList<>();
    }

    public static boolean isSameOrganization(Organization o1, Organization o2){
        if(o1 instanceof SecurityForce && o2 instanceof SecurityForce)
            return true;
        else if(o1 instanceof EmergencyCenter && o2 instanceof EmergencyCenter)
            return true;
        else
            return false;
    }

    public List<RegularUser> getUsers(){
        return users;
    }

    public void addOrganizationAtEvent(OrganizationAtEvent oae){
        // TODO: 14-Jun-19 //check
        events.add(oae);
    }

    public boolean addUser(RegularUser user){
        // TODO: 14-Jun-19
        return false;
    }

    public boolean removeUser(RegularUser user){
        // TODO: 14-Jun-19
        return false;
    }


}
