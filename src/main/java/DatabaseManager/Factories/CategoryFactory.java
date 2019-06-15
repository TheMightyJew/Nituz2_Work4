package DatabaseManager.Factories;

import Categories.Category;

public class CategoryFactory {
    private static CategoryFactory ourInstance = new CategoryFactory();

    public static CategoryFactory getInstance() {
        return ourInstance;
    }

    private CategoryFactory() {
    }

    public Category Build(String Name){
        return new Category(Name);
    }
}
