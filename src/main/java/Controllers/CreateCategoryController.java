package Controllers;

import Categories.Category;
import DatabaseManager.CategoriesTableManager;

import java.util.Date;
import java.util.List;

public class CreateCategoryController extends Controller{

    public boolean addNewCategory(String username,String category){
        // TODO: 14-Jun-19
        try{
            CategoriesTableManager.getInstance().CreateANewCategory(new Category(category));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
