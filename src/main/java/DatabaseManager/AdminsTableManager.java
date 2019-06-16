package DatabaseManager;

import DatabaseManager.Factories.AdminFactories.AdminFactory;
import Users.Admins.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminsTableManager extends DatabaseController {
    private static AdminsTableManager ourInstance = new AdminsTableManager();

    public static AdminsTableManager getInstance() {
        return ourInstance;
    }

    private AdminsTableManager() {
    }

    public List<Admin> getAllAdmins() {
        connect();
        String sql = "SELECT In_Charge_Of FROM Admins";
        List<Admin> admins = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String In_Charge_Of = rs.getString("In_Charge_Of");

                Admin curr = AdminFactory.getInstance().Build(In_Charge_Of);
                admins.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return admins;
    }

    public Admin getAdminByUsername(String username) {
        connect();
        String sql = "SELECT In_Charge_Of FROM Admins WHERE Username = ?";
        Admin admin = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                String Organization_Name = rs.getString("In_Charge_Of");

                admin = AdminFactory.getInstance().Build(Organization_Name);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return admin;
    }

    public Admin getAdminByUsernameWithoutCreatingOrg(String username) {
        connect();
        String sql = "SELECT Password, Mail_Address, Type FROM Registered_Users INNER JOIN Admins ON Registered_Users.Username=Admins.Username WHERE Admins.Username = ?";
        Admin admin = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                String password = rs.getString("Password");
                String mail = rs.getString("Mail_Address");
                String type = rs.getString("Type");

                admin = AdminFactory.getInstance().Build(username, password, mail, type);
                break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return admin;
    }
}
