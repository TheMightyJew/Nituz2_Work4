package Events;

import java.util.Date;

public class Event {

    enum EventStatus{
        InProgress,Done;
    }

    private String title;
    private Date publishTime;
    private EventStatus status;

}