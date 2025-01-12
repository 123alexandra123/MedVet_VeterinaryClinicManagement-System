package vet.system1;

import java.sql.*;

/**
 * Represents a database connection for the veterinary clinic application.
 * Establishes a connection to the MySQL database and provides a statement for executing queries.
 */
public class db_conn {

    /**
     * The database connection object.
     */
    Connection connection;

    /**
     * The statement object used for executing SQL queries.
     */
    Statement statement;

    /**
     * Initializes a new database connection to the "clinica_veterinara" database.
     * The connection is established using the provided database URL, username, and password.
     * If an error occurs during connection, the stack trace is printed.
     */
    public db_conn() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica_veterinara", "root", "654321Alex");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
