import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static java.sql.Connection getConnection(String hostname, String login, String pass) throws ClassNotFoundException, SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        String Url = "jdbc:postgresql://localhost:5432/postgres";
        java.sql.Connection connection = DriverManager.getConnection(Url, login, pass);
        return connection;
    }
}
