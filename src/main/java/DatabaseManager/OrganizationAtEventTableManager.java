package DatabaseManager;

import DatabaseManager.Factories.OrganizationAtEventFactory;
import Events.OrganizationAtEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationAtEventTableManager extends DatabaseController {
    private static OrganizationAtEventTableManager ourInstance = new OrganizationAtEventTableManager();

    public static OrganizationAtEventTableManager getInstance() {
        return ourInstance;
    }

    private OrganizationAtEventTableManager() {
    }

    public List<OrganizationAtEvent> getOrganizationAtEventForOrganization(String Organization_Name){
        connect();
        String sql = "SELECT Event_Title, In_Charge FROM Organization_At_Event WHERE Organization_Name = ?";
        List<OrganizationAtEvent> organizationAtEvents = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Organization_Name);
            ResultSet rs = pstmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String Event_Title = rs.getString("Event_Title");
                String In_Charge = rs.getString("In_Charge");

                OrganizationAtEvent curr = OrganizationAtEventFactory.getInstance().Build(Organization_Name, Event_Title, In_Charge);
                organizationAtEvents.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return organizationAtEvents;
    }
}
