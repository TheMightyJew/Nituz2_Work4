package DatabaseManager;

import Categories.Category;
import DatabaseManager.Factories.CategoryFactory;
import DatabaseManager.Factories.EventsFactory;
import Events.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelationEventCategoryTableManager extends DatabaseController {
    private static RelationEventCategoryTableManager ourInstance = new RelationEventCategoryTableManager();

    public static RelationEventCategoryTableManager getInstance() {
        return ourInstance;
    }

    private RelationEventCategoryTableManager() {
    }

    public List<Event> getEventsForCategory(Category category){
        connect();
        String sql = "SELECT Event_Title FROM Relation_Event_Category WHERE Category_Name = ?";
        List<Event> events = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            ResultSet rs = pstmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String Event_Title = rs.getString("Event_Title");

                Event curr = EventsTableManager.getInstance().getEventByTitleForCategory(Event_Title, category);
                events.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return events;
    }

    public List<Category> getCategoriesForEvent(Event event){
        connect();
        String sql = "SELECT Category_Name FROM Relation_Event_Category WHERE Event_Title = ?";
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, event.getTitle());
            ResultSet rs = pstmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String Category_Name = rs.getString("Category_Name");

                Category curr = CategoryFactory.getInstance().Build(Category_Name);
                categories.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return categories;
    }

    public void CreateNewRelation(Event event, Category category){
        connect();
        String sql = "INSERT INTO Relation_Event_Category (Event_Title, Category_Name) VALUES (?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, event.getTitle());
            pstmt.setString(2, category.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();
    }
}
