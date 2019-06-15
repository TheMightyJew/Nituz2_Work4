package DatabaseManager;

import DatabaseManager.Factories.AdminFactories.AdminFactory;
import Users.Admins.Admin;

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
        String sql = "SELECT Username, Password, Mail_Address, Type, In_Charge_Of FROM Registered_Users INNER JOIN Admins ON Registered_Users.Username=Admins.Username";
        List<Admin> admins = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String mail = rs.getString("Mail_Address");
                String type = rs.getString("Type");
                String In_Charge_Of = rs.getString("In_Charge_Of");

                Admin curr = AdminFactory.getInstance().Build(username, password, mail, type, In_Charge_Of);
                admins.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return admins;
    }
}
