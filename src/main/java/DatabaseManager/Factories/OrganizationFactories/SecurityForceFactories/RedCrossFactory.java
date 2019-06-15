package DatabaseManager.Factories.OrganizationFactories.SecurityForceFactories;

import DatabaseManager.Factories.OrganizationFactories.OrganizationBuilder;
import Organizations.Organization;
import Organizations.SecurityForces.RedCross;

public class RedCrossFactory implements OrganizationBuilder {
    private static RedCrossFactory ourInstance = new RedCrossFactory();

    public static RedCrossFactory getInstance() {
        return ourInstance;
    }

    private RedCrossFactory() {
    }

    @Override
    public Organization Build(String Name, String Type, String Admin_Username) {
        return new RedCross(Name);
    }
}
