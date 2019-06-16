package DatabaseManager;

import DatabaseManager.Factories.OrganizationFactories.OrganizationFactory;
import Organizations.Organization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrganizationsTableManager extends DatabaseController{
    private static OrganizationsTableManager ourInstance = new OrganizationsTableManager();

    public static OrganizationsTableManager getInstance() {
        return ourInstance;
    }

    private OrganizationsTableManager() {
    }

    public List<Organization> getAllOrganizations(){
        connect();
        String sql = "SELECT Name, Type, Admin_Username FROM Organizations";
        List<Organization> organizations = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String Name = rs.getString("Name");
                String Type = rs.getString("Type");
                String Admin_Username = rs.getString("Admin_Username");

                Organization curr = OrganizationFactory.getInstance().Build(Name, Type, Admin_Username);
                organizations.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return organizations;
    }

    public Organization getOrganizationByName(String organization_name) {
        connect();
        String sql = "SELECT Type, Admin_Username FROM Organizations WHERE Name = ?";
        Organization organization = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, organization_name);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                String Type = rs.getString("Type");
                String Admin_Username = rs.getString("Admin_Username");

                organization = OrganizationFactory.getInstance().Build(organization_name, Type, Admin_Username);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return organization;
    }
}
