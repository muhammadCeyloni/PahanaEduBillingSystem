package dao;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import util.DBConnectionUtil;

class DBConnectionTest {

    @Test
    void testDatabaseConnection() {
        try (Connection conn = DBConnectionUtil.getConnection()) {
            assertNotNull(conn, "Connection should not be null");
            System.out.println("✅ Connected to pahana_edu database!");
        } catch (Exception e) {
            fail("❌ Database connection failed: " + e.getMessage());
        }
    }
}
