package DatabaseManager;

import Categories.Category;
import DatabaseManager.Factories.CategoryFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriesTableManager extends DatabaseController {
    private static CategoriesTableManager ourInstance = new CategoriesTableManager();

    public static CategoriesTableManager getInstance() {
        return ourInstance;
    }

    private CategoriesTableManager() {
    }

    public List<Category> getAllCategories() {
        connect();
        String sql = "SELECT Name FROM Category";
        List<Category> categories = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                String Name = rs.getString("Name");

                Category curr = CategoryFactory.getInstance().Build(Name);
                categories.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        disconnect();
        return categories;
    }

    public void CreateANewCategory(Category category){
        connect();
        String sql = "INSERT INTO Category (Name) VALUES (?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();
    }

}
