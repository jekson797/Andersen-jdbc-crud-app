package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CitiesTable extends DataBaseTable {

    private Connection connection;

    public CitiesTable(Connection connection) {
        this.connection = connection;
    }

    public void update(String oldCityName, String newCityName) {
        String query = "UPDATE Cities "
                + "SET name = ? WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newCityName);
            statement.setString(2, oldCityName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String select() {
        String query = "SELECT * FROM Cities";
        String result = "";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            result = getStringFormResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void insert(String... params) {
        if (params.length != 2) {
            System.err.println("Wrong parameters amount(1 = cityName, 2 = population 3 = oldCityName)");
        } else if (!isSecondParameterNumber(params[1])) {
            System.err.println("Wrong population parameter(1 = cityName, 2 = population 3 = oldCityName)");
        } else {
            String query = "INSERT INTO Cities (name, population)"
                    + "VALUES (?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, params[0]);
                statement.setString(2, params[1]);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(String cityToDelete) {
        String query = "DELETE FROM Cities WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cityToDelete);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isSecondParameterNumber(String parametr) {
        try {
            Integer.valueOf(parametr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getStringFormResultSet(ResultSet resultSet) throws SQLException {
        StringBuilder table = new StringBuilder();
        while (resultSet.next()) {
            table.append(resultSet.getString("name")).append(" || ").
                    append(resultSet.getInt("population")).append("\n");
        }
        return table.toString();
    }
}
