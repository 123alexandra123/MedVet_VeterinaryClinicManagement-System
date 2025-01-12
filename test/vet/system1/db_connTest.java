package vet.system1;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class db_connTest {

    @Test
    public void testDatabaseConnection() {
        db_conn conn = new db_conn();
        Connection connection = conn.connection;
        assertNotNull(connection, "Database connection should not be null");
        try {
            assertFalse(connection.isClosed(), "Database connection should be open");
        } catch (Exception e) {
            fail("Exception while testing database connection: " + e.getMessage());
        }
    }
}
