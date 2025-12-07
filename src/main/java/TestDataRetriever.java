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

            List<Product> products = dataRetriever.getProductList(1, 10);
            if (products.isEmpty()) {
                System.out.println("Aucun produit trouvé");
            } else {
                System.out.println("Liste des produits :");
                System.out.println(products);
            }

        }
    }