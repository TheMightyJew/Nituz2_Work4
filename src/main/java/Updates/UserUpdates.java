package Updates;

import java.util.ArrayList;
import java.util.List;

public class UserUpdates {

    private int ID;
    private UserAtEvent user;
    private List<Update> updates;

    public UserUpdates(UserAtEvent user) {
        this.ID = -1;
        this.user = user;
        updates = new ArrayList<Update>();
    }

    public UserUpdates(int ID, UserAtEvent user, List<Update> updates) {
        this.ID = ID;
        this.user = user;
        this.updates = updates;
    }

    public UserAtEvent getUser() {
        return user;
    }

    public List<Update> getUpdates() {
        return updates;
    }

    public void addUpdate(Update update){
        updates.add(update);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
