package TwitterPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        String dbURL = "jdbc:mysql://localhost:3306/twitter";

        String username = "twitter";
        String password = "test";
        Connection connection = DriverManager.getConnection(
                dbURL, username, password);
        return connection;
    }
}
