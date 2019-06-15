package DatabaseManager.Factories.OrganizationFactories;

import Organizations.Organization;

public interface OrganizationBuilder {
    Organization Build(String Name, String Type, String Admin_Username);
}
