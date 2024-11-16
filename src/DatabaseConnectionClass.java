// DatabaseConnection.java - Create this as a separate file
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionClass {
    // Database credentials - update these with your MySQL credentials
    private static final String URL = "jdbc:mysql://localhost:3306/fedhadb";
    private static final String USER = "root";  // Replace with your MySQL username
    private static final String PASSWORD = "@Keykey001";  // Replace with your MySQL password

    private static Connection connection = null;

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("MySQL JDBC Driver not found.");
            throw new RuntimeException("MySQL JDBC Driver not found.");
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
            }
            return connection;
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            throw e;
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
































//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseConnectionClass {
//    // Database credentials
//    private static final String URL = "jdbc:mysql://localhost:3306/fedhadb";
//    private static final String USER = "root";
//    private static final String PASSWORD = "@Keykey001";
//
//    private static Connection connection = null;
//
//    static {
//        try {
//            // Load the MySQL JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.err.println("MySQL JDBC Driver not found.");
//            throw new RuntimeException("MySQL JDBC Driver not found.");
//        }
//    }
//
//    public static Connection getConnection() throws SQLException {
//        if (connection == null || connection.isClosed()) {
//            try {
//                connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            } catch (SQLException e) {
//                System.err.println("Failed to connect to database!");
//                throw e;
//            }
//        }
//        return connection;
//    }
//
//    public static void closeConnection() {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
