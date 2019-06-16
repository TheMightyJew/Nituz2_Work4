package DatabaseManager;

import DatabaseManager.Factories.RegularUserFactories.RegularUserFactory;
import Users.RegularUsers.RegularUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegularUsersTableManager extends DatabaseController {
    private static RegularUsersTableManager ourInstance = new RegularUsersTableManager();

    public static RegularUsersTableManager getInstance() {
        return ourInstance;
    }

    private RegularUsersTableManager() {
    }

    public List<RegularUser> getAllUsers() {
        connect();
        String sql = "SELECT Username, Password, Mail_Address, Degree, Status, Type, Organization_Name FROM Registered_Users INNER JOIN Regular_Users ON Registered_Users.Username=Regular_Users.Username";
        List<RegularUser> regularUsers = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String mail = rs.getString("Mail_Address");
                int degree = rs.getInt("Degree");
                int status = rs.getInt("Status");
                String type = rs.getString("Type");
                String Organization_Name = rs.getString("Organization_Name");

                RegularUser curr = RegularUserFactory.getInstance().Build(username, password, mail, degree, status, type, Organization_Name);
                regularUsers.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return regularUsers;
    }

    public RegularUser getUserByUsername(String username) {
        connect();
        String sql = "SELECT Password, Mail_Address, Degree, Status, Type, Organization_Name FROM Registered_Users INNER JOIN Regular_Users ON Registered_Users.Username=Regular_Users.Username WHERE Regular_Users.Username = ?";
        RegularUser regularUser = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                String password = rs.getString("Password");
                String mail = rs.getString("Mail_Address");
                int degree = rs.getInt("Degree");
                int status = rs.getInt("Status");
                String type = rs.getString("Type");
                String Organization_Name = rs.getString("Organization_Name");

                regularUser = RegularUserFactory.getInstance().Build(username, password, mail, degree, status, type, Organization_Name);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return regularUser;
    }
}
