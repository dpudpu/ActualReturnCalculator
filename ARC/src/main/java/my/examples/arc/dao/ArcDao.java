package my.examples.arc.dao;

import my.examples.arc.servlet.ArcDto;
import my.examples.arc.servlet.MyGoodsListDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArcDao {
    String host = "thjang-arc-20181008.mysql.database.azure.com";
    String database = "arc";
    String user = "arc@thjang-arc-20181008";
    String password = "school1017!";
//    private String dbUrl = "jdbc:mysql://thjang-arc-20181008.mysql.database.azure.com/arc?userSSL=false";
//    private String dbId = "arc@thjang-arc-20181008";
//    private String dbPassword = "school1017!";

    public List<MyGoodsListDto> getMyGoodsListDto() {
        List<MyGoodsListDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            String url = String.format("jdbc:mysql://%s/%s", host, database);
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");
            properties.setProperty("serverTimezone","UTC");

//            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            conn = DbUtil.connect(url, properties);
            String sql ="SET @rownum:=0;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            sql =   "SELECT @rownum:=@rownum+1, \n" +
                    "\tm.gds_nm , g.prf_rto, g.cms, inv.inv_prod, inv.my_inv_prc, g.cms \n" +
                    "FROM my_inv_lst inv, inv_gds_lst g INNER JOIN gds_mst m \n" +
                    "ON g.gds_cd=m.gds_cd \n" +
                    "where inv.gds_cd = g.gds_cd; \n";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                MyGoodsListDto myGoodsListDto = new MyGoodsListDto();

                myGoodsListDto.setRownum(rs.getInt(1));
                myGoodsListDto.setGoodsName(rs.getString(2));
                myGoodsListDto.setPrfRto(rs.getLong(3));
                myGoodsListDto.setInvestPeriod(rs.getInt(4));
                myGoodsListDto.setMyPrice(rs.getInt(5));
                myGoodsListDto.setCms(rs.getDouble("g.cms"));
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
}