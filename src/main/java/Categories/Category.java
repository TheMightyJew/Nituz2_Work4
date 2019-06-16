package Categories;

import Events.Event;

import java.util.*;

public class Category {

    private String name;
    private List<Event> events;

    public Category(String name) {
        this.name = name;
        events = new ArrayList<Event>();
    }

    public String getName() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public boolean addEvent(Event event){
        events.add(event);
        return true;
    }

}
