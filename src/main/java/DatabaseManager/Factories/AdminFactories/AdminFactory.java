package DatabaseManager.Factories.AdminFactories;

import Users.Admins.Admin;

public class AdminFactory implements AdminBuilder{
    private static AdminFactory ourInstance = new AdminFactory();

    public static AdminFactory getInstance() {
        return ourInstance;
    }

    private AdminFactory() {
    }

    @Override
    public Admin Build(String Username, String Password, String Mail_Address, String Type, String Organization_Name) {
        if(Type.equals("Emergency_Center")){
            return EmergencyCenterAdminFactory.getInstance().Build(Username, Password, Mail_Address, Type, Organization_Name);
        }
        else{
            //if(Type.equals("Security_Force"))
            return SecurityForceAdminFactory.getInstance().Build(Username, Password, Mail_Address, Type, Organization_Name);
        }
    }
}
