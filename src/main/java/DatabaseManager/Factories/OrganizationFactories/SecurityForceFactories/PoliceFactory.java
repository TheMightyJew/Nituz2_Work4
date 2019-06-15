package DatabaseManager.Factories.OrganizationFactories.SecurityForceFactories;

import DatabaseManager.Factories.OrganizationFactories.OrganizationBuilder;
import Organizations.Organization;
import Organizations.SecurityForces.Police;

public class PoliceFactory implements OrganizationBuilder {
    private static PoliceFactory ourInstance = new PoliceFactory();

    public static PoliceFactory getInstance() {
        return ourInstance;
    }

    private PoliceFactory() {
    }

    @Override
    public Organization Build(String Name, String Type, String Admin_Username) {
        return new Police(Name);
    }
}
