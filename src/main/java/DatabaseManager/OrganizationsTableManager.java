package DatabaseManager;

import DatabaseManager.Factories.OrganizationFactories.OrganizationFactory;
import Organizations.Organization;

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
}
