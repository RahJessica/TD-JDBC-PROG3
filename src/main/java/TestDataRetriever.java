import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TestDataRetriever {

        public static void main(String[] args) {

            DataRetriever dataRetriever = new DataRetriever();

            List<Category> categories = dataRetriever.getAllCategories();

            if (categories.isEmpty()) {
                System.out.println("Aucune catégorie trouvée");
            } else {
                System.out.println("Catégories trouvées :");
                for (Category c : categories) {
                    System.out.println(c.getName());
                }
            }

            List<Product> products = dataRetriever.getProductList(1, 2 );
            if (products.isEmpty()) {
                System.out.println("Aucun produit trouvé");
            } else {
                System.out.println("Liste des produits :");
                System.out.println(products);
            }

            // test des filtres :
            List<Product> allProducts = dataRetriever.getProductsByCriteria(
                    null,
                    null,
                    null,
                    null
            );
            allProducts.forEach(System.out::println);

            List<Product> productFilter = dataRetriever.getProductsByCriteria(
                    "pc",
                    null,
                    null,
                    null
            );
            productFilter.forEach(System.out::println);

            List<Product> categoryFilter = dataRetriever.getProductsByCriteria(
                    null,
                    "informatique",
                    null,
                    null
            );
            categoryFilter.forEach(System.out::println);

            List<Product> dateMinFilter = dataRetriever.getProductsByCriteria(
                    null,
                    null,
                    Instant.parse("2024-01-01T00:00:00Z"),
                    null
            );
            dateMinFilter.forEach(System.out::println);

            List<Product> combinedFilter = dataRetriever.getProductsByCriteria(
                    "pc",
                    "informatique",
                    Instant.parse("2024-01-01T00:00:00Z"),
                    Instant.now()
            );
            combinedFilter.forEach(System.out::println);

        }
    }