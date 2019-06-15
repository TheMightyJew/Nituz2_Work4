package DatabaseManager.Factories;

import Events.OrganizationAtEvent;

public class OrganizationAtEventFactory {
    private static OrganizationAtEventFactory ourInstance = new OrganizationAtEventFactory();

    public static OrganizationAtEventFactory getInstance() {
        return ourInstance;
    }

    private OrganizationAtEventFactory() {
    }

    public OrganizationAtEvent Build(String organization_name, String event_title, String in_charge) {
        // TODO: 6/15/2019
        return null;
    }
}
