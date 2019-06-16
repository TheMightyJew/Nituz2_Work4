package Controllers;

import Categories.Category;
import DatabaseManager.CategoriesTableManager;

public class CreateCategoryController extends Controller{

    public boolean addNewCategory(String category){
        try{
            CategoriesTableManager.getInstance().CreateANewCategory(new Category(category));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
