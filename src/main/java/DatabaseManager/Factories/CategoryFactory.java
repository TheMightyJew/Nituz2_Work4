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
        // TODO: 6/15/2019 maybe need to add all of the events that have this category - need to check it
        return new Category(Name);
    }
}
