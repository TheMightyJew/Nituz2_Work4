package Users.RegularUsers;

import Categories.Category;
import Controllers.ChangePasswordController;
import Controllers.CreateEventController;
import Organizations.Organization;
import Updates.UpdateData;

import java.util.Date;
import java.util.List;

public class EmergencyCenterUser extends RegularUser {

    private CreateEventController createEventController;
    private ChangePasswordController changePasswordController;

    public EmergencyCenterUser(ChangePasswordController changePasswordController,CreateEventController createEventController, String username, String mailAddress, String password, Organization organization,String degree) {
        super(changePasswordController, username, mailAddress, password, organization,degree);
        this.createEventController = createEventController;
    }

    public boolean addEvent(String title, UpdateData updateData, List<Category> categories, SecurityForceUser securityForceUser) {
        return createEventController.createNewEvent(this,title,updateData,categories,securityForceUser);
    }
}
