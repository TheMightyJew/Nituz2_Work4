package Updates;

import Events.Event;
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
}
