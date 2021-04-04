package pos.java.jdbc.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.util.Objects.isNull;

public class SingleConnection {

    private static Connection connection = null;
    private static String url = "jdbc:postgresql://localhost:5432/posjava";
    private static String user = "admin";
    private static String password = "admin";

    static {
        conectar();
    }

    public SingleConnection() {
        conectar();
    }

    private static void conectar() {
        try {
            if (isNull(connection)) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
