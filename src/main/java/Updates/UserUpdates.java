package Updates;

import java.util.ArrayList;
import java.util.List;

public class UserUpdates {

    private UserAtEvent user;
    private List<Update> updates;

    public UserUpdates(UserAtEvent user) {
        this.user = user;
        updates = new ArrayList<Update>();
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
}
