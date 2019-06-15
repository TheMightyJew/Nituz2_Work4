package DatabaseManager.Factories.OrganizationFactories;

import DatabaseManager.AdminsTableManager;
import DatabaseManager.Factories.OrganizationFactories.SecurityForceFactories.SecurityForceFactory;
import Organizations.Organization;
import Users.Admins.Admin;

public class OrganizationFactory implements OrganizationBuilder {
    private static OrganizationFactory ourInstance = new OrganizationFactory();

    public static OrganizationFactory getInstance() {
        return ourInstance;
    }

    private OrganizationFactory() {
    }

    @Override
    public Organization Build(String Name, String Type, String Admin_Username) {
        Organization organization;
        if(Type.equals("Emergency_Center")){
            organization = EmergencyCenterFactory.getInstance().Build(Name, Type, Admin_Username);
        }
        else{
            //if(Type.contains("Security_Force")
            organization = SecurityForceFactory.getInstance().Build(Name, Type, Admin_Username);
        }
        //connect the organization with it's admin...
        Admin admin = AdminsTableManager.getInstance().getAdminByUsernameWithoutCreatingOrg(Admin_Username);
        organization.setAdmin(admin);
        admin.setOrganization(organization);
        return organization;
    }
}
