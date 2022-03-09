import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String url = "jdbc:mysql://localhost:3306/campus_assistance?useSSL=false";
    private static String driverClass = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "815635";
    private static Connection conn;

    //装载驱动
    static {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取数据库连接
    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //建立数据库连接
    public static void main(String[] args) {
        Connection conn = DBUtil.getConnection();
        if (conn != null) {
            System.out.println("connect success!");
        } else {
            System.out.println("connect fail!");
        }
    }

    //关闭数据库连接
    public static void Close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}