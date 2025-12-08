import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
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

    List<Product> getProductsByCriteria(String productName, String categoryName, Instant creationMin, Instant creationMax) {
        List<Product> products = new ArrayList<>();

        //StringBuilder : pour construire des requêtes SQL longues
        StringBuilder sql = new StringBuilder("""
        SELECT p.id AS product_id,
               p.name AS product_name,
               p.creation_datetime,
               c.id AS category_id,
               c.name AS category_name
               FROM product p
               JOIN product_category c ON c.product_id = p.id
               WHERE 1 = 1
    """);

        List<Object> params = new ArrayList<>(); // stockage des valeurs de ? dans l'ordre

        if (productName != null) {
            sql.append(" AND p.name ILIKE ? "); // ajout au StringBuilder
            params.add("%" + productName + "%");
        }

        if (categoryName != null) {
            sql.append(" AND c.name ILIKE ? ");
            params.add("%" + categoryName + "%");
        }

        if (creationMin != null) {
            sql.append(" AND p.creation_datetime >= ? ");
            params.add(java.sql.Timestamp.from(creationMin));
        }

        if (creationMax != null) {
            sql.append(" AND p.creation_datetime <= ? ");
            params.add(java.sql.Timestamp.from(creationMax));
        }

        try (
                Connection conn = DBConnection.getDBConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())
        ) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    Category category = new Category(
                            rs.getInt("category_id"),
                            rs.getString("category_name")
                    );

                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getTimestamp("creation_datetime").toInstant(),
                            category
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
