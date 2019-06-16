package DatabaseManager;

import DatabaseManager.Factories.UpdateDataFactory;
import Updates.UpdateData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UpdateDataTableManager extends DatabaseController{
    private static UpdateDataTableManager ourInstance = new UpdateDataTableManager();

    public static UpdateDataTableManager getInstance() {
        return ourInstance;
    }

    private UpdateDataTableManager() {
    }

    public UpdateData getUpdateDataByID(int updateDataID) {
        connect();
        String sql = "SELECT Data, Data_Date FROM Update_Data WHERE ID = ?";

        UpdateData ans = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, updateDataID);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                if(ans != null)
                    throw new UnsupportedOperationException("Should not be here");

                String Data = rs.getString("Data");
                String Data_Date = rs.getString("Data_Date");

                ans = UpdateDataFactory.getInstance().Build(updateDataID, Data, Data_Date);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return ans;
    }

    public int CreateANewUpdateData(UpdateData updateData){
        int nextUpdateDataID = getNextUpdateDataID();

        connect();
        String sql = "INSERT INTO Update_Data (ID, Data, Data_Date) VALUES (?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nextUpdateDataID);
            pstmt.setString(2, updateData.getData());
            pstmt.setString(3, updateData.getDate().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();

        return nextUpdateDataID;
    }

    private int getNextUpdateDataID() {
        connect();
        String sql = "SELECT MAX(ID) AS Max_ID FROM Update_Data";
        int nextUpdateDataID = 0;
        boolean wasHere = false;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                if(wasHere)
                    throw new UnsupportedOperationException("Should not be here");
                wasHere = true;
                nextUpdateDataID = rs.getInt("Max_ID") + 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        disconnect();

        return nextUpdateDataID;
    }
}
