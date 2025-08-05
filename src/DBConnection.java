// Import necessary classes from the java.sql package for database connectivity.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A utility class for managing the connection to the MySQL database.
 * This class centralizes the database connection logic, making it easier to manage
 * and reuse throughout the application.
 */
public class DBConnection {

    // A constant holding the JDBC connection URL.
    // Format: jdbc:mysql://[hostname]:[port]/[databaseName]
    private static final String URL = "jdbc:mysql://localhost:3306/contact_book";

    // A constant for the MySQL database username.
    // !! IMPORTANT: Replace "root" with your actual MySQL username.
    private static final String USER = "root";

    // A constant for the MySQL database password.
    // !! IMPORTANT: Replace "YourPassword" with your actual MySQL password.
    private static final String PASSWORD = "Reerevhb@2004";

    /**
     * Establishes and returns a connection to the database.
     * This is a static method, so it can be called directly using the class name: DBConnection.getConnection()
     *
     * @return a Connection object to the database, or null if the connection fails.
     */
    public static Connection getConnection() {
        try {
            // Step 1: Load and register the MySQL JDBC driver.
            // Class.forName() dynamically loads the driver class into memory.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection using the DriverManager.
            // It uses the URL, username, and password to connect to the database.
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            // Handle potential exceptions.
            // ClassNotFoundException: Thrown if the JDBC driver JAR is not in the classpath.
            // SQLException: Thrown for database-related errors (e.g., wrong credentials, server down).
            System.out.println("Database Connection Error: " + e.getMessage());
            e.printStackTrace(); // Prints the full stack trace for debugging purposes.
            return null; // Return null to indicate that the connection attempt failed.
        }
    }
}