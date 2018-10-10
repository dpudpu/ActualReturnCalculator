package my.examples.arc.dao;

import java.sql.*;
import java.util.Properties;

public class DbUtil {
//    public static Connection connect(String dbUrl, String dbId, String dbPassword)
    public static Connection connect(String dbUrl, Properties properties)
            throws RuntimeException {
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, properties);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return conn;
    }

    //insert, update, delete
    public static void close(Connection conn, PreparedStatement ps) {
        if(ps != null) {
            try{
                ps.close();
            }catch (SQLException ex) {}
        }
        if(conn != null) {
            try{
                conn.close();
            }catch (SQLException ex) {}
        }
    } //close

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if(rs != null) {
            try{
                rs.close();
            }catch (SQLException ex) {}
        }
        close(conn, ps);
    } //close
}