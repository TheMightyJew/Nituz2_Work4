package DatabaseManager.Factories.AdminFactories;

import Users.Admins.Admin;

public class SecurityForceAdminFactory implements AdminBuilder {
    private static SecurityForceAdminFactory ourInstance = new SecurityForceAdminFactory();

    public static SecurityForceAdminFactory getInstance() {
        return ourInstance;
    }

    private SecurityForceAdminFactory() {
    }

    @Override
    public Admin Build(String Username, String Password, String Mail_Address, String Type, String Organization_Name) {
        // TODO: 6/15/2019
        return null;
    }
}
