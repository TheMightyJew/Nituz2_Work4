package DatabaseManager.Factories.AdminFactories;

import DatabaseManager.OrganizationsTableManager;
import Organizations.Organization;
import Users.Admins.Admin;

public class AdminFactory implements AdminBuilder{
    private static AdminFactory ourInstance = new AdminFactory();

    public static AdminFactory getInstance() {
        return ourInstance;
    }

    private AdminFactory() {
    }

    public Admin Build(String Organization_Name) {
        Organization organization = OrganizationsTableManager.getInstance().getOrganizationByName(Organization_Name);
        return organization.getAdmin();
    }

    @Override
    public Admin Build(String username, String password, String mail, String type) {
        if(type.equals("Emergency_Center")){
            return EmergencyCenterAdminFactory.getInstance().Build(username, password, mail, type);
        }
        else{
            //if(type.equals("Security_Force"))
            return SecurityForceAdminFactory.getInstance().Build(username, password, mail, type);
        }
    }
}
