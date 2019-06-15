package Controllers;

import Categories.Category;
import DatabaseManager.EventsTableManager;
import Events.Event;
import Updates.Update;
import Updates.UpdateData;
import Users.Admins.EmergencyCenterAdmin;
import Users.RegularUsers.EmergencyCenterUser;
import Users.RegularUsers.SecurityForceUser;

import java.util.Date;
import java.util.List;

public class CreateEventController extends Controller {

    public boolean createNewEvent(EmergencyCenterUser creator, Date date, String title, UpdateData updateData, List<Category> categories, SecurityForceUser securityForceUser){
        try {
            Event event = new Event(creator,title,new Update(null,updateData),securityForceUser,categories);
            EventsTableManager.getInstance().CreateANewEvent(event);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
