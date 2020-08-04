import table.CitiesTable;
import table.DataBaseTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            DataBaseTable table = new CitiesTable(connection);
            System.out.println(table.select());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
