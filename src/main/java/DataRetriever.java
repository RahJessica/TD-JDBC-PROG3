import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM product_category";

        try (
                Connection conn = DBConnection.getDBConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                categories.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public List<Product> getProductList(int page, int size) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, creation_datetime FROM product ORDER BY id LIMIT ? OFFSET ?";
        int offset = (page - 1) * size;

        try (
                Connection conn = DBConnection.getDBConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, size);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getTimestamp("creation_datetime").toInstant()
                    );
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    public static void main(String[] args) {

    }
}
