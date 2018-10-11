package my.examples.arc.dao;

import my.examples.arc.dto.ARCGdsMstDto;
import my.examples.arc.dto.ARCInvInputDto;
import my.examples.arc.dto.MyGoodsListDto;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArcDao {
    private String dbURL=null;
    private Properties properties;

    public ArcDao() {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            properties = new Properties();
            properties.load(in);
            dbURL = String.format("jdbc:mysql://%s/%s", properties.getProperty("host"), properties.getProperty("database"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int addMyGoodsList(ARCInvInputDto arcInvInputDtoParam) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;
        ARCInvInputDto arcInvInputDto = arcInvInputDtoParam;
        conn = DbUtil.connect(dbURL, properties);
        try {
            String sql = null;

            sql = "INSERT INTO my_inv_lst ( id, gds_cd, inv_prod, my_inv_prc)"
                    + "VALUES(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "kimId");
            ps.setInt(2, arcInvInputDto.getInvPrdIdx());
            ps.setInt(3, arcInvInputDto.getInvPeriod());
            ps.setInt(4, arcInvInputDto.getInvMoney());
            count = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }

        return count;
    }

    public List<ARCGdsMstDto> getAllGoodsListDto() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ARCGdsMstDto> list = new ArrayList<>();
        conn = DbUtil.connect(dbURL, properties);
        try {
            String sql = null;
            sql = "SELECT * FROM gds_mst ORDER BY gds_cd ASC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ARCGdsMstDto arcGdsMstDto = new ARCGdsMstDto();
                arcGdsMstDto.setGds_cd(rs.getInt(1));
                arcGdsMstDto.setGds_nm(rs.getString(2));
                list.add(arcGdsMstDto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
        return list;
    }

    public List<MyGoodsListDto> getMyGoodsListDto(String pg) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = DbUtil.connect(dbURL,properties);

        List<MyGoodsListDto> list = new ArrayList<>();
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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = DbUtil.connect(dbURL,properties);

        int cnt=0;
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