package dao;   // if you chose dao package, otherwise remove this line

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("✅ Connected to pahana_edu database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
