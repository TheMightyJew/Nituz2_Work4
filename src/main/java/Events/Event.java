package Events;

import Categories.Category;
import Organizations.Organization;
import Organizations.SecurityForces.SecurityForce;
import Updates.Update;
import Updates.UserAtEvent;
import Users.Admins.EmergencyCenterAdmin;
import Users.RegularUsers.EmergencyCenterUser;
import Users.RegularUsers.SecurityForceUser;

import java.util.*;

public class Event {

    public enum EventStatus{
        InProgress,Done;
    }

    private EmergencyCenterUser creator;
    private String title;
    private Date publishTime;
    private EventStatus status;
    private Update initialUpdate;
    private Update lastUpdate;
    private List<UserAtEvent> users;
    private List<OrganizationAtEvent> organizations;
    private Map<Organization,UserAtEvent> organizationsAdmins;
    private List<Category> categories;

    public Event(EmergencyCenterUser creator, String title, Update initialUpdate, SecurityForceUser user, List<Category> categories) {
        this.creator = creator;
        this.title = title;
        this.initialUpdate = initialUpdate;
        //this.publishTime = Data.now or something;
        status = EventStatus.InProgress;
        lastUpdate = initialUpdate;
        users = new ArrayList<UserAtEvent>();
        organizations = new ArrayList<OrganizationAtEvent>();
        organizationsAdmins = new HashMap<Organization, UserAtEvent>();
        addUser(user);
        this.categories = categories;

    }

    private void addUser(SecurityForceUser user) {
        UserAtEvent userAtEvent = new UserAtEvent(user,this);
        users.add(userAtEvent);
        Organization organization = user.getOrganization();
        if(organization instanceof SecurityForce){
            if(organizationsAdmins.containsKey(organization)==false){
                organizationsAdmins.put(organization,userAtEvent);
                OrganizationAtEvent organizationAtEvent = new OrganizationAtEvent(organization,this, userAtEvent);
                organizations.add(organizationAtEvent);
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public EventStatus getStatus() {
        return status;
    }

    public EmergencyCenterUser getCreator() {
        return creator;
    }

    public Update getInitialUpdate() {
        return initialUpdate;
    }

    public Update getLastUpdate() {
        return lastUpdate;
    }

    public List<UserAtEvent> getUsers() {
        return users;
    }

    public List<OrganizationAtEvent> getOrganizations() {
        return organizations;
    }

    public Map<Organization, UserAtEvent> getOrganizationsAdmins() {
        return organizationsAdmins;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public void setLastUpdate(Update lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public void setStatus(int status) {
        if(status == 0)
            this.status = EventStatus.InProgress;
        else
            this.status = EventStatus.Done;
    }

    public int getStatusInt(){
        if(status == EventStatus.InProgress)
            return 0;
        else
            return 1;
    }
}
