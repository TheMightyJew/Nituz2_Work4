package Updates;

import Events.Event;
import Events.OrganizationAtEvent;
import Users.RegularUsers.RegularUser;

public class UserAtEvent {

    private RegularUser user;
    private Event event;
    private UserUpdates updates;

    public UserAtEvent(RegularUser user, Event event) {
        this.user = user;
        this.event = event;
        updates = new UserUpdates(this);
    }

    public UserAtEvent(RegularUser user) {
        this.user = user;
        this.event = null;
        updates = new UserUpdates(this);
    }

    public RegularUser getUser() {
        return user;
    }

    public void setUser(RegularUser user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserUpdates getUpdates() {
        return updates;
    }

    public void setUpdates(UserUpdates updates) {
        this.updates = updates;
    }
}
