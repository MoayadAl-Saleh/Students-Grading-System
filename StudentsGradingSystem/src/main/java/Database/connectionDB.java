package Database;
// Java program to search the contents of
// a table in JDBC Connection class for JDBC
// Connection class of JDBC

// Importing required classes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {

final String DB_URL
        = "jdbc:mysql://localhost:3306/gradingsystem";
final String USER = "root";

final String PASS = "12345";

public Connection connectDB()
    {

    Connection con = null;

    try {

    Class.forName("com.mysql.cj.jdbc.Driver");

    con = DriverManager.getConnection(DB_URL, USER,
            PASS);
    }

    catch (SQLException e) {

    e.printStackTrace();
    }

    catch (ClassNotFoundException e) {

    e.printStackTrace();
    }
    return con;
    }
}