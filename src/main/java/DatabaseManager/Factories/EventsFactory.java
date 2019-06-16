package DatabaseManager.Factories;

import Categories.Category;
import DatabaseManager.*;
import Events.Event;
import Updates.Update;
import Updates.UserAtEvent;
import Users.RegularUsers.EmergencyCenterUser;
import Users.RegularUsers.SecurityForceUser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(Publish_Time,formatter);

        event.setPublishTime(convertToDateViaInstant(dateTime));
        event.setStatus(Status);
        creator.setEvent(event);
        inChargeFromOrg.setEvent(event);
        return event;

    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public Event Build(String event_title, String publish_time, int status, int first_update, Category category) {
        // TODO: 6/15/2019
        return null;
    }
}
