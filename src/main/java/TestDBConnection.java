import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getDBConnection()) {

            if (conn != null && !conn.isClosed()) {
                System.out.println("Connexion à la base de données réussie");
            }

        } catch (SQLException e) {
            System.out.println("Échec de la connexion");
            e.printStackTrace();
        }
    }
}
