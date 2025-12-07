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
        }
    }