package DatabaseManager.Factories.AdminFactories;

import Users.Admins.Admin;
import Users.Admins.SecurityForceAdmin;

public class SecurityForceAdminFactory implements AdminBuilder {
    private static SecurityForceAdminFactory ourInstance = new SecurityForceAdminFactory();

    public static SecurityForceAdminFactory getInstance() {
        return ourInstance;
    }

    private SecurityForceAdminFactory() {
    }

    @Override
    public Admin Build(String Username, String Password, String Mail_Address, String Type) {
        return new SecurityForceAdmin(null, Username, Mail_Address, Password, null);
    }
}
