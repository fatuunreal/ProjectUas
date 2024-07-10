package models;

import java.sql.*;

public class Users {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1/uas?autoReconnect=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "";

    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;

    public User checkUser(String uname, String paswd) {
        User user = null;
        try {
            System.out.println("Loading JDBC driver...");
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database.");

            String sql = "SELECT id, username FROM login WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, paswd);

            System.out.println("Executing query...");
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("User found.");
                String id = rs.getString("id");
                String username = rs.getString("username");
                user = new User(id, username);
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return user;
    }
}
