package Categories;

import Events.Event;

import java.util.*;

public class Category {

    private String name;
    private List<Event> events;
    private static Map<String ,Category> categories;

    public Category(String name) {
        this.name = name;
        events = new ArrayList<Event>();
        categories.put(name,this);
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

    public static Category getCategory(String name){
        if(categoryExsists(name))
            return categories.get(name);
        return null;
    }

    public static boolean categoryExsists(String name){
        if(categories.containsKey(name)){
            return true;
        }
        else
            return false;
    }

    public static List<Category> getCategories(){
        return new ArrayList<Category>(categories.values());
    }


}
