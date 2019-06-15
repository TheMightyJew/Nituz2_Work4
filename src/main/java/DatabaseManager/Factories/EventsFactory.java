package DatabaseManager.Factories;

import Events.Event;

public class EventsFactory {
    private static EventsFactory ourInstance = new EventsFactory();

    public static EventsFactory getInstance() {
        return ourInstance;
    }

    private EventsFactory() {
    }

    public Event Build(String Title, String Publish_Time, int Status, int First_Update){
        // TODO: 6/15/2019
        return null;
    }
}
