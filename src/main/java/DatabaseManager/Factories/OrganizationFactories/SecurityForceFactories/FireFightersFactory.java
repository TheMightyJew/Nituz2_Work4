package DatabaseManager.Factories.OrganizationFactories.SecurityForceFactories;

import DatabaseManager.Factories.OrganizationFactories.OrganizationBuilder;
import Organizations.Organization;
import Organizations.SecurityForces.FireFighters;

public class FireFightersFactory implements OrganizationBuilder {
    private static FireFightersFactory ourInstance = new FireFightersFactory();

    public static FireFightersFactory getInstance() {
        return ourInstance;
    }

    private FireFightersFactory() {
    }

    @Override
    public Organization Build(String Name, String Type, String Admin_Username) {
        return new FireFighters(Name);
    }
}
