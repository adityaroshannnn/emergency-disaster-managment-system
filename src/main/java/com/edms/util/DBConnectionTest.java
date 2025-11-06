package com.edms.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionTest {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            System.out.println("Connected: " + (con != null));

            ResultSet rs = st.executeQuery("SELECT DATABASE() db, NOW() now");
            if (rs.next()) {
                System.out.println("DB=" + rs.getString("db") + " | Time=" + rs.getString("now"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}