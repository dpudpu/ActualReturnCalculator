package my.examples.arc.dao;

import my.examples.arc.dto.ARCGdsMstDto;
import my.examples.arc.dto.MyGoodsListDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestListBoardDao {

    public List<ARCGdsMstDto> getAllGoodsList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<ARCGdsMstDto> list = new ArrayList<>();
        conn = DbUtil.connect();
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

    public List<MyGoodsListDto> getMyGoodsList(String pg, int posts) {
        Connection conn ;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = DbUtil.connect();

        List<MyGoodsListDto> list = new ArrayList<>();
        try{
            String sql=null;
            // 투자리스트 게시판
            sql =   "SELECT m.gds_nm , gds.prf_rto, inv.inv_prod, inv.my_inv_prc, gds.cms\n" +
                    "FROM my_inv_lst inv, inv_gds_lst gds INNER JOIN gds_mst m ON gds.gds_cd = m.gds_cd\n" +
                    "WHERE inv.gds_cd = gds.gds_cd LIMIT ?,?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(pg)*posts-(posts-1));
            ps.setInt(2,Integer.parseInt(pg)*posts);
            rs = ps.executeQuery();

            int rowNum=0;
            while(rs.next()) {
                MyGoodsListDto myGoodsListDto = new MyGoodsListDto();
                myGoodsListDto.setRownum(rowNum++);
                myGoodsListDto.setGoodsName(rs.getString("gds_nm"));
                myGoodsListDto.setPrfRto(rs.getLong("prf_rto"));
                myGoodsListDto.setInvestPeriod(rs.getInt("inv_prod"));
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
        conn = DbUtil.connect();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnt;
    }
}
