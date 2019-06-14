package Events;

import java.util.Date;

public class Event {

    public enum EventStatus{
        InProgress,Done;
    }

    private String title;
    private Date publishTime;
    private EventStatus status;

    public String getTitle() {
        return title;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public EventStatus getStatus() {
        return status;
    }
}
