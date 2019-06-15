package DatabaseManager.Factories.OrganizationFactories;

import Organizations.EmergencyCenter;
import Organizations.Organization;

public class EmergencyCenterFactory implements OrganizationBuilder{
    private static EmergencyCenterFactory ourInstance = new EmergencyCenterFactory();

    public static EmergencyCenterFactory getInstance() {
        return ourInstance;
    }

    private EmergencyCenterFactory() {
    }

    @Override
    public Organization Build(String Name, String Type, String Admin_Username) {
        return new EmergencyCenter(Name);
    }
}
