package configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySQL {
    private static String USERNAME = "root";
    private static String PASSWORD = "admin123";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3307/LTWEB";

    public static Connection getDatabaseConnection() throws SQLException {

        try {
            Class.forName(DRIVER);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Test chương trình. Kích phải chuột chọn run as->java application

    public static void main(String[] args) {

        try {
            new DBConnectMySQL();
            System.out.println(DBConnectMySQL.getDatabaseConnection());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

