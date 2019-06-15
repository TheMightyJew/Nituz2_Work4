package DatabaseManager.Factories.AdminFactories;

import Users.Admins.Admin;
import Users.Admins.EmergencyCenterAdmin;

public class EmergencyCenterAdminFactory implements AdminBuilder {
    private static EmergencyCenterAdminFactory ourInstance = new EmergencyCenterAdminFactory();

    public static EmergencyCenterAdminFactory getInstance() {
        return ourInstance;
    }

    private EmergencyCenterAdminFactory() {
    }

    @Override
    public Admin Build(String Username, String Password, String Mail_Address, String Type) {
        return new EmergencyCenterAdmin(null, Username, Mail_Address, Password, null, null);
    }
}
