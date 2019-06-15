package Users.Admins;

import Categories.Category;
import Controllers.ChangePasswordController;
import Controllers.CreateCategoryController;
import Controllers.CreateEventController;
import Organizations.Organization;
import Updates.UpdateData;
import Users.RegularUsers.SecurityForceUser;

import java.util.Date;
import java.util.List;

public class EmergencyCenterAdmin extends Admin {

    private CreateCategoryController createCategoryController;

    public EmergencyCenterAdmin(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization, CreateCategoryController createCategoryController) {
        super(changePasswordController, username, mailAddress, password, organization);
        this.createCategoryController = createCategoryController;
    }

    public boolean addCategory(String category){
        return createCategoryController.addNewCategory(username,category);
    }

}
