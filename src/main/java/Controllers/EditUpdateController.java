package Controllers;

import DatabaseManager.UpdatesTableManager;
import Events.Event;
import Updates.UpdateData;
import Users.RegisteredUser;

public class EditUpdateController extends Controller {
    private ViewController viewController;

    public EditUpdateController(ViewController viewController) {
        this.viewController = viewController;
    }

    public boolean Edit(Event event, UpdateData updateData){
        try{
            if(Massage.confirmMassage("Are you sure you want to edit the update?")){
                UpdatesTableManager.getInstance().EditAnUpdate(event.getLastUpdate().getID(),updateData);
                Massage.infoMassage("Update was edited successfully");
                viewController.updateEvents();
                return true;
            }
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
