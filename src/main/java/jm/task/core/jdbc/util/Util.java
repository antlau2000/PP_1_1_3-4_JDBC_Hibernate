package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private final static String URL =
            "jdbc:mysql://localhost:3306/mysqltest";
    private final static String URL_FIXED =
            "jdbc:mysql://localhost:3306/mysqltest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_FIXED, USERNAME, PASSWORD);
    }
}
