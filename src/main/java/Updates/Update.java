package Updates;

import Events.Event;

public class Update {

    private Event event;
    private Update previous;
    private Update next;
    private UpdateData data;
    private UpdateData firstData;

    public Update(Event event, Update previous, UpdateData data) {
        this.event = event;
        this.previous = previous;
        this.firstData = data;
        this.data = this.firstData;
        next = null;
    }

    public Event getEvent() {
        return event;
    }

    public Update getPrevious() {
        return previous;
    }

    public Update getNext() {
        return next;
    }

    public UpdateData getData() {
        return data;
    }

    public UpdateData getFirstData() {
        return firstData;
    }

    public void setNext(Update next) {
        this.next = next;
    }

    public void setData(UpdateData data) {
        this.data = data;
    }
}
