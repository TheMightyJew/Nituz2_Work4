package DatabaseManager;

import Categories.Category;
import DatabaseManager.Factories.EventsFactory;
import Events.Event;
import Events.OrganizationAtEvent;
import Updates.UserAtEvent;
import Updates.UserUpdates;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventsTableManager extends DatabaseController{
    private static EventsTableManager ourInstance = new EventsTableManager();

    public static EventsTableManager getInstance() {
        return ourInstance;
    }

    private EventsTableManager() {
    }

    public List<Event> getAllEvents() {
        connect();
        String sql = "SELECT Title, Publish_Time, Status, First_Update FROM Events";
        List<Event> events = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String Title = rs.getString("Title");
                String Publish_Time = rs.getString("Publish_Time");
                int Status = rs.getInt("Status");
                int First_Update = rs.getInt("First_Update");

                Event curr = EventsFactory.getInstance().Build(Title, Publish_Time, Status, First_Update);
                events.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return events;
    }

    public List<Event> getAllEventsForUsername(String Username) {
        connect();
        String sql = "SELECT Title, Publish_Time, Status, First_Update FROM Events INNER JOIN User_At_Event ON Events.Title=User_At_Event.Event_Title WHERE User_At_Event.Regular_User_Username = ?";
        List<Event> events = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Username);
            ResultSet rs = pstmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String Title = rs.getString("Title");
                String Publish_Time = rs.getString("Publish_Time");
                int Status = rs.getInt("Status");
                int First_Update = rs.getInt("First_Update");

                Event curr = EventsFactory.getInstance().Build(Title, Publish_Time, Status, First_Update);
                events.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return events;
    }

    public void CreateANewEvent(Event event) {
        int updateID = UpdatesTableManager.getInstance().CreateANewUpdateFirstForTheEvent(event.getInitialUpdate());

        connect();
        String sql = "INSERT INTO Events (Title, Publish_Time, Status ,First_Update) VALUES (?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, event.getTitle());
            pstmt.setString(2, event.getPublishTime().toString());
            pstmt.setInt(3, event.getStatusInt());
            pstmt.setInt(4, updateID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();

        UserAtEvent creator = event.getUsers().get(0);
        //create the user_Updates
        UserUpdates userUpdates = creator.getUpdates();
        UserUpdatesTableManager.getInstance().CreateUserUpdates(userUpdates, updateID);
        //create the user_at_event and organization_at_event
        UserAtEventTableManager.getInstance().CreateUserAtEvent(creator);
        OrganizationAtEvent creators_organization = event.getOrganizations().get(0);
        OrganizationAtEventTableManager.getInstance().CreateOrganizationAtEvent(creators_organization);
        //add the categories to the category list of the event
        for(Category category : event.getCategories()) {
            RelationEventCategoryTableManager.getInstance().CreateNewRelation(event, category);
        }
    }

    public Event getEventByTitleForCategory(String event_title, Category category) {
        connect();
        String sql = "SELECT Publish_Time, Status, First_Update FROM Events WHERE Title = ?";
        Event event = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, event_title);
            ResultSet rs = pstmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                if(event != null)
                    throw new UnsupportedOperationException("Should not be here");

                String Publish_Time = rs.getString("Publish_Time");
                int Status = rs.getInt("Status");
                int First_Update = rs.getInt("First_Update");

                event = EventsFactory.getInstance().Build(event_title, Publish_Time, Status, First_Update, category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return event;
    }
}
