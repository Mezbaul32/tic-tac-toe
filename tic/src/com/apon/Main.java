package com.apon;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        new LoginFrame();

        try (Connection conn = Database.connect()) {
            System.out.println("✅ Connected to MySQL (XAMPP)!");
        } catch (SQLException e) {
            System.out.println("❌ Connection failed:");
            e.printStackTrace();
        }
    }
}
