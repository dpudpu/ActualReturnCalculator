package my.examples.arc.dao;

import my.examples.arc.dto.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class MemberDao {
    private String dbURL;
    private Properties properties;

    public MemberDao(){
        GetProperties gp = GetProperties.getInstance();
        dbURL = gp.getDbURL();
        properties = gp.getProperties();
    }


    public int signUp(MemberDto memberDto){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = DbUtil.connect(dbURL,properties);

        try {

            String sql = "INSERT INTO (id, name, pw, email)\n" +
                    "VALUES(?,?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"1");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }


        return 1;
    }
}
