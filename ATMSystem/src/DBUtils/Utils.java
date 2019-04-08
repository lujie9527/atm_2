package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/atm?useUnicode=true&characterEncoding=UTF-8";
    static String username = "root";
    static String password = "123456";

    static {
        try {
            Class.forName(driver);//加载驱动程序
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);//连接数据库
    }

    public static void main(String[] args) {
        try {
            System.out.println(Utils.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
