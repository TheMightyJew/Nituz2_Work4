package DatabaseManager;

import Categories.Category;
import DatabaseManager.Factories.EventsFactory;
import Events.Event;

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

    public void CreateANewEvent(Event event) {
        // TODO: 6/15/2019 create the initial update
        int updateID = 0;

        connect();
        String sql = "INSERT INTO Events (Title, Publish_Time, Status ,First_Update) VALUES (?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, event.getTitle());
            pstmt.setString(2, event.getPublishTime().toString());
            pstmt.setInt(3, event.getStatus().ordinal());
            pstmt.setInt(4, updateID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();

        // TODO: 6/15/2019 create the user_at_event and organization_at_event
        // TODO: 6/15/2019 add the category to the category list of the event
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
