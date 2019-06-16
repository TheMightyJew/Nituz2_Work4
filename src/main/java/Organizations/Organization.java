package Organizations;

import DatabaseManager.OrganizationAtEventTableManager;
import DatabaseManager.OrganizationsTableManager;
import DatabaseManager.UserAtEventTableManager;
import Events.OrganizationAtEvent;
import Updates.UserAtEvent;
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
        OrganizationAtEventTableManager.getInstance().CreateOrganizationAtEvent(oae);
        events.add(oae);
    }

    public boolean addUser(RegularUser user){
        try{
            // TODO: 16-Jun-19
            users.add(user);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    public boolean removeUser(RegularUser user){
        try{
            // TODO: 16-Jun-19
            users.remove(user);
            return true;
        }
        catch (Exception e){
            return false;
        }
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
