package Controllers;

import DatabaseManager.UpdatesTableManager;
import Events.Event;
import Updates.UpdateData;
import Users.RegisteredUser;

public class EditUpdateController extends Controller {

    public boolean Edit(RegisteredUser user, Event event, UpdateData updateData){
        try{
            // TODO: 16-Jun-19
            UpdatesTableManager.getInstance().EditAnUpdate(event.getLastUpdate().getID(),updateData);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
}
