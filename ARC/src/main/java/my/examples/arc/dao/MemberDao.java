package my.examples.arc.dao;

import my.examples.arc.dto.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Connection conn;
        PreparedStatement ps = null;
        conn = DbUtil.connect(dbURL,properties);
        int count=0;

        try {

            String sql = "INSERT INTO MEMBER (id, name, pw, email)\n" +
                    "VALUES(?,?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1,memberDto.getId());
            ps.setString(2,memberDto.getName());
            ps.setString(3,memberDto.getPassword());
            ps.setString(4,memberDto.getEmail());
            count = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }


        return count;
    }

    public int login(MemberDto memberDto) {
        Connection conn;
        PreparedStatement ps=null;
        ResultSet rs=null;
        conn=DbUtil.connect(dbURL,properties);


        try {
            String sql = "SELECT * FROM MEMBER WHERE id=? AND pw=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,memberDto.getId());
            ps.setString(2,memberDto.getPassword());
            rs = ps.executeQuery();
            if (rs.next()){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(conn, ps,rs);
        }

        return -1;
    }
}
