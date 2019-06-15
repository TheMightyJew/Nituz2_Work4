package DatabaseManager.Factories;

import Categories.Category;
import DatabaseManager.*;
import Events.Event;
import Updates.Update;
import Updates.UserAtEvent;

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
        //EmergencyCenterAdmin admin, String title, Update initialUpdate, SecurityForceUser user, List<Category> categories
        UserAtEvent creator = UserAtEventTableManager.getInstance().getUserAtEventForEvent(Title);
        Update initialUpdate = UpdatesTableManager.getInstance().getUpdateByIDWithoutEvent(First_Update);
        List<Category> categories = RelationEventCategoryTableManager.getInstance().getCategoriesForEvent(Title);
        //Event event = new Event();
        //event.setPublishTime(Date.valueOf(Publish_Time));
        //event.setStatus(Status);
        return null;
    }

    public Event Build(String event_title, String publish_time, int status, int first_update, Category category) {
        // TODO: 6/15/2019
        return null;
    }
}
