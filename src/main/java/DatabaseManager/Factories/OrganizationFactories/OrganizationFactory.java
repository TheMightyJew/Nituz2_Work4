package DatabaseManager.Factories.OrganizationFactories;

import DatabaseManager.Factories.OrganizationFactories.SecurityForceFactories.SecurityForceFactory;
import Organizations.Organization;

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
        // TODO: 6/15/2019 connect the organization with it's admin and users...
        return organization;
    }
}
