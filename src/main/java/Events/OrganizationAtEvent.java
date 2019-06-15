package Events;

import Events.Event;
import Organizations.Organization;
import Updates.UserAtEvent;

public class OrganizationAtEvent {

    private Organization organization;
    private Event event;
    private UserAtEvent inCharge;

    public OrganizationAtEvent(Organization organization, Event event, UserAtEvent inCharge) {
        this.organization = organization;
        this.event = event;
        this.inCharge = inCharge;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserAtEvent getInCharge() {
        return inCharge;
    }

    public void setInCharge(UserAtEvent inCharge) {
        this.inCharge = inCharge;
    }
}
