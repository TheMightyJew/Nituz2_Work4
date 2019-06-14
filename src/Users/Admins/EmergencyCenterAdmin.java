package Users.Admins;

import Controllers.ChangePasswordController;
import Controllers.CreateCategoryController;
import Controllers.CreateEventController;
import Organizations.Organization;

public class EmergencyCenterAdmin extends Admin {

    private CreateEventController createEventController;
    private CreateCategoryController createCategoryController;

    public EmergencyCenterAdmin(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization, CreateEventController createEventController, CreateCategoryController createCategoryController) {
        super(changePasswordController, username, mailAddress, password, organization);
        this.createEventController = createEventController;
        this.createCategoryController = createCategoryController;
    }
}
