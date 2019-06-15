package Events;

import Events.Event;
import Organizations.Organization;

public class OrganizationAtEvent {

    private Organization organization;
    private Event event;

    public OrganizationAtEvent(Organization organization, Event event) {
        this.organization = organization;
        this.event = event;
    }
}
