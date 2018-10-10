package my.examples.arc.dao;

import my.examples.arc.servlet.ArcDto;
import my.examples.arc.servlet.MyGoodsListDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArcDao {
    private String dbUrl = "jdbc:mysql://localhost:3306/test?userSSL=false";
    private String dbId = "mysql";
    private String dbPassword = "mysql";

    public List<ArcDto> getArcDtoList() {
        List<ArcDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql = "select case when a.gds_cd=1 then 'sangpum1'\n" +
                    "\twhen a.gds_cd=2 then 'sangpum2'\n" +
                    "\twhen a.gds_cd=3 then 'sangpum3'\n" +
                    "\twhen a.gds_cd=4 then 'sangpum4'\n" +
                    "\t\tEND as '상품코드', \n" +
                    "\ta.inv_prod as '투자기간(개월)',\n" +
                    "\tb.prf_rto as '수익률', \n" +
                    "\ta.my_inv_prc as '투자금액'\n" +
                    "from my_inv_lst a, inv_gds_lst b \n" +
                    "where a.gds_cd = b.gds_cd";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                ArcDto arcDto = new ArcDto();
                arcDto.setGdsNm(rs.getString(1));
                arcDto.setInvestPeriod(rs.getInt(2));
                arcDto.setPrfRto(rs.getLong(3));
                arcDto.setMyPrice(rs.getInt(4));
                list.add(arcDto);
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            DbUtil.close(conn, ps, rs);
        }
        return list;
    }

    /*INV_GDS_LST
    igl_idx	int(10) unsigned
    gds_cd	int(10)
    prf_rto	float(7,2)
    cms	double(10,2)

    MEMBER
    mb_idx	int(10) unsigned
    id	varchar(20)
    name	varchar(20)
    pw	varchar(20)
    email	varchar(50)

    MY_INV_LST
    my_idx	int(10) unsigned
    id	varchar(20)
    gds_cd	varchar(20)
    inv_prod	int(10)
    my_inv_prc	double(10,2)*/


  /*
    public int addGoods(ArcDto arcDto) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql = "insert into inv_gds_lst (gds_cd, prf_rto, cms)\n" +
                            "values (?, ?, ?)";
            //db에넣어야지
            ps = conn.prepareStatement(sql);
            ps.setInt(1, arcDto.getIdx());
            ps.setLong(2, arcDto.getPrfRto());
            ps.setFloat(3, arcDto.getCms());
            count = ps.executeUpdate();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            DbUtil.close(conn, ps);
        }
        return count;
    }
    
    //내투자목록상품등록
    public int addMyGoods(ArcDto arcDto) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql = "insert into my_inv_lst (id, gds_cd, inv_prod, my_inv_prc)\n" +
                            "values (?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, arcDto.getId());
            ps.setInt(2, arcDto.getGdsCd());
            ps.setInt(3, arcDto.getInvestPeriod());
            ps.setDouble(4, arcDto.getInvestPrice());
            count = ps.executeUpdate();
            */
    public List<MyGoodsListDto> getMyGoodsListDto() {
        List<MyGoodsListDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
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
