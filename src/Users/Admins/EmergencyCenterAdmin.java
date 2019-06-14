package Users.Admins;

import Controllers.Category;
import Controllers.ChangePasswordController;
import Controllers.CreateCategoryController;
import Controllers.CreateEventController;
import Organizations.Organization;
import Updates.UpdateData;
import Users.RegularUsers.SecurityForceUser;

import java.util.Date;
import java.util.List;

public class EmergencyCenterAdmin extends Admin {

    private CreateEventController createEventController;
    private CreateCategoryController createCategoryController;

    public EmergencyCenterAdmin(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization, CreateEventController createEventController, CreateCategoryController createCategoryController) {
        super(changePasswordController, username, mailAddress, password, organization);
        this.createEventController = createEventController;
        this.createCategoryController = createCategoryController;
    }

    public boolean addCategory(String category){
        return createCategoryController.addNewCategory(username,category);
    }

    public boolean addEvent(Date date, String title, UpdateData updateData, List<Category> categories, SecurityForceUser securityForceUser) {
        return createEventController.createNewEvent(this,date,title,updateData,categories,securityForceUser);
    }
}
