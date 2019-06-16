package Users.Admins;

import Controllers.ChangePasswordController;
import Controllers.CreateCategoryController;
import Organizations.Organization;

public class EmergencyCenterAdmin extends Admin {

    private CreateCategoryController createCategoryController;

    public EmergencyCenterAdmin(ChangePasswordController changePasswordController, String username, String mailAddress, String password, Organization organization, CreateCategoryController createCategoryController) {
        super(changePasswordController, username, mailAddress, password, organization);
        this.createCategoryController = createCategoryController;
    }

    public boolean addCategory(String category){
        return createCategoryController.addNewCategory(category);
    }

}
