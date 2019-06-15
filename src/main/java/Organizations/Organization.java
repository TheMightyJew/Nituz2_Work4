package Organizations;

import Events.OrganizationAtEvent;
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
        users = new ArrayList<RegularUser>();
        events = new ArrayList<OrganizationAtEvent>();
    }

    public static boolean isSameOrganization(Organization o1, Organization o2){
        /*if(o1 instanceof SecurityForce && o2 instanceof SecurityForce)
            return true;
        else if(o1 instanceof EmergencyCenter && o2 instanceof EmergencyCenter)
            return true;
        else
            return false;*/
        return o1.getClass().equals(o2.getClass());
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public abstract String toString();

    public String getId() {
        return id;
    }
}
