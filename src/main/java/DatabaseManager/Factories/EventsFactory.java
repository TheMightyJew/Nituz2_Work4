package DatabaseManager.Factories;

import Categories.Category;
import DatabaseManager.*;
import Events.Event;
import Updates.Update;
import Updates.UserAtEvent;
import Users.RegularUsers.EmergencyCenterUser;
import Users.RegularUsers.SecurityForceUser;

import java.sql.Date;
import java.util.List;

public class EventsFactory {
    private static EventsFactory ourInstance = new EventsFactory();

    public static EventsFactory getInstance() {
        return ourInstance;
    }

    private EventsFactory() {
    }

    public Event Build(String Title, String Publish_Time, int Status, int First_Update){
        List<UserAtEvent> users = UserAtEventTableManager.getInstance().getUserAtEventForEvent(Title);
        UserAtEvent creator = null;
        UserAtEvent inChargeFromOrg = null;
        for(UserAtEvent curr : users){
            if(curr.getUser() instanceof EmergencyCenterUser)
                creator = curr;
            else
                inChargeFromOrg = curr;
        }
        Update initialUpdate = UpdatesTableManager.getInstance().getUpdateByIDWithoutEvent(First_Update);
        List<Category> categories = RelationEventCategoryTableManager.getInstance().getCategoriesForEvent(Title);
        Event event = new Event((EmergencyCenterUser)creator.getUser(), Title, initialUpdate, (SecurityForceUser)inChargeFromOrg.getUser(), categories);
        event.setPublishTime(Date.valueOf(Publish_Time));
        event.setStatus(Status);
        creator.setEvent(event);
        inChargeFromOrg.setEvent(event);
        return event;

    }

    public Event Build(String event_title, String publish_time, int status, int first_update, Category category) {
        // TODO: 6/15/2019
        return null;
    }
}
