package DatabaseManager;

import DatabaseManager.Factories.UpdatesFactory;
import Events.Event;
import Updates.Update;
import Updates.UpdateData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UpdatesTableManager extends DatabaseController{
    private static UpdatesTableManager ourInstance = new UpdatesTableManager();

    public static UpdatesTableManager getInstance() {
        return ourInstance;
    }

    private UpdatesTableManager() {
    }

    public List<Update> getAllUpdatesForAnEvent(String Event_Title) {
        connect();
        String sql = "SELECT Update_ID, First_Update_Data, Last_Update_Data, User_Updates FROM Update WHERE Event_Title = ?";
        List<Update> updates = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Event_Title);
            ResultSet rs = pstmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                int Update_ID = rs.getInt("Update_ID");
                int First_Update_Data = rs.getInt("First_Update_Data");
                int Last_Update_Data = rs.getInt("Last_Update_Data");
                int User_Updates = rs.getInt("User_Updates");

                Update curr = UpdatesFactory.getInstance().Build(Update_ID, First_Update_Data, Last_Update_Data, User_Updates, Event_Title);
                updates.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return updates;
    }

    public int CreateANewUpdateFirstForTheEvent(Update update){
        int nextUpdateID = getNextUpdateID();
        update.setID(nextUpdateID);

        //create first update data
        int firstUpdateDataID = UpdateDataTableManager.getInstance().CreateANewUpdateData(update.getFirstData());

        connect();
        String sql = "INSERT INTO Update (Update_ID, First_Update_Data, Last_Update_Data, Next_For_User, Next_For_Event, User_Updates, Event_Title) VALUES (?,?,NULL,NULL,NULL,NULL,NULL)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nextUpdateID);
            pstmt.setInt(2, firstUpdateDataID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();

        return nextUpdateID;
    }

    public void EditAnUpdate(int updateID, UpdateData newData){
        //create last update data
        int lastUpdateData = UpdateDataTableManager.getInstance().CreateANewUpdateData(newData);

        connect();
        String sql = "UPDATE Update SET Last_Update_Data = ? WHERE Update_ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, lastUpdateData);
            pstmt.setInt(2, updateID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();
    }

    private int getNextUpdateID() {
        connect();
        String sql = "SELECT MAX(Update_ID) AS Max_ID FROM Update";
        int nextUpdateID = 0;
        boolean wasHere = false;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                if(wasHere)
                    throw new UnsupportedOperationException("Should not be here");
                wasHere = true;
                nextUpdateID = rs.getInt("Max_ID") + 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        disconnect();

        return nextUpdateID;
    }

    public Update getUpdateByIDWithoutEvent(int updateID) {
        connect();
        String sql = "SELECT First_Update_Data, Last_Update_Data, User_Updates FROM Update WHERE Update_ID = ?";
        Update update = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, updateID);
            ResultSet rs = pstmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                int First_Update_Data = rs.getInt("First_Update_Data");
                int Last_Update_Data = rs.getInt("Last_Update_Data");
                int User_Updates = rs.getInt("User_Updates");

                update = UpdatesFactory.getInstance().Build(updateID, First_Update_Data, Last_Update_Data, User_Updates);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return update;
    }
}
