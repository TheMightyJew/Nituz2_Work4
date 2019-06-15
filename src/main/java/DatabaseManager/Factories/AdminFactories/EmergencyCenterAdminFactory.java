package DatabaseManager.Factories.AdminFactories;

import Users.Admins.Admin;

public class EmergencyCenterAdminFactory implements AdminBuilder {
    private static EmergencyCenterAdminFactory ourInstance = new EmergencyCenterAdminFactory();

    public static EmergencyCenterAdminFactory getInstance() {
        return ourInstance;
    }

    private EmergencyCenterAdminFactory() {
    }

    @Override
    public Admin Build(String Username, String Password, String Mail_Address, String Type, String Organization_Name) {
        // TODO: 6/15/2019  
        return null;
    }
}
