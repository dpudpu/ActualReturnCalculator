package my.examples.arc.dao;

import my.examples.arc.servlet.MyGoodsListDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArcDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String url =null;
    private Properties properties=null;

    private static final String host = "thjang-arc-20181008.mysql.database.azure.com";
    private static final String database = "arc";
    private static final String user = "arc@thjang-arc-20181008";
    private static final String password = "school1017!";

    // 생성과 동시에 DB 접속
    public ArcDao() {
        url = String.format("jdbc:mysql://%s/%s", host, database);
        properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        properties.setProperty("useSSL", "true");
        properties.setProperty("verifyServerCertificate", "true");
        properties.setProperty("requireSSL", "false");
        properties.setProperty("serverTimezone","UTC");
    }

    public List<MyGoodsListDto> getMyGoodsListDto(String pg) {
        List<MyGoodsListDto> list = new ArrayList<>();
        conn = DbUtil.connect(url, properties);
        try{
            String sql=null;


            // 투자리스트 게시판
            sql ="SET @rownum:=0;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            sql =   "SELECT A.*\n" +
                    "FROM (SELECT @rownum:=@rownum+1 AS ROW_NUM,\n" +
                    "m.gds_nm , gds.prf_rto, inv.inv_prod, inv.my_inv_prc, gds.cms\n" +
                    "FROM my_inv_lst inv, inv_gds_lst gds INNER JOIN gds_mst m ON gds.gds_cd = m.gds_cd\n" +
                    "WHERE inv.gds_cd = gds.gds_cd)A \n" +
                    "WHERE A.ROW_NUM BETWEEN ? AND ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1,(Integer.parseInt(pg)*5-4)+"");
            ps.setString(2,Integer.parseInt(pg)*5+"");
            rs = ps.executeQuery();

            while(rs.next()) {
                MyGoodsListDto myGoodsListDto = new MyGoodsListDto();
                myGoodsListDto.setRownum(rs.getInt(1));
                myGoodsListDto.setGoodsName(rs.getString(2));
                myGoodsListDto.setPrfRto(rs.getLong(3));
                myGoodsListDto.setInvestPeriod(rs.getInt(4));
                myGoodsListDto.setMyPrice(rs.getInt("A.my_inv_prc"));
                myGoodsListDto.setCms(rs.getDouble("cms"));
                myGoodsListDto.setProfits(myGoodsListDto.getMyPrice()+myGoodsListDto.getMyPrice()*myGoodsListDto.getPrfRto()/100);
                list.add(myGoodsListDto);
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            DbUtil.close(conn, ps, rs);
        }
        return list;
    }

    public int getCnt(){
        int cnt=0;
        conn = DbUtil.connect(url, properties);
        try {
            // 총 개수 Query
            String sql ="SELECT COUNT(*)\n" +
                    "FROM my_inv_lst inv, inv_gds_lst gds \n" +
                    "WHERE inv.gds_cd = gds.gds_cd;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {

                cnt=rs.getInt(1);
            }
            System.out.println(cnt);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return cnt;
    }

}