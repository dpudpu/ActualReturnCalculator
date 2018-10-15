package my.examples.arc.dao;

import my.examples.arc.dto.ARCInvInputDto;
import my.examples.arc.dto.ARCReplyDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class GoodsPostDao {

    public int addMyGoodsList(ARCInvInputDto arcInvInputDtoParam) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;
        ARCInvInputDto arcInvInputDto = arcInvInputDtoParam;
        conn = DbUtil.connect();
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
            throw new RuntimeException();
        } finally {
            DbUtil.close(conn, ps);
        }

        return count;
    }

    // 댓글 등록 SQL 전송
    public int addReply(ARCReplyDto arcReplyDtoParam) {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = DbUtil.connect();

        int count = 0;
        ARCReplyDto arcReplyDto = arcReplyDtoParam;

        try {
            String sql = "INSERT INTO mb_rpy (mb_idx, prt_idx, content)"
                    + "VALUES(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, arcReplyDto.getMemberIdx());
            ps.setInt(2, arcReplyDto.getParentIdx());
            ps.setString(3, arcReplyDto.getContent());
            count = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        } finally {
            DbUtil.close(conn, ps);
        }
        return count;
    }

    // 댓글 가져오기
    public List<ARCReplyDto> getReply() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+9"));

        conn = DbUtil.connect();

        List<ARCReplyDto> list = new ArrayList<>();

        try {
            String sql = "SELECT member.mb_idx, id, rpy_idx, prt_idx, content, rpy_time\n"
                    + "FROM member INNER JOIN mb_rpy ON member.mb_idx=mb_rpy.mb_idx";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ARCReplyDto arcReplyDto = new ARCReplyDto();
                arcReplyDto.setMemberIdx(rs.getInt(1));
                arcReplyDto.setMemberId(rs.getString(2));
                arcReplyDto.setReplyIdx(rs.getInt(3));
                arcReplyDto.setParentIdx(rs.getInt(4));
                arcReplyDto.setContent(rs.getString(5));
                arcReplyDto.setReplyTime(rs.getTimestamp(6, cal).getTime());

                list.add(arcReplyDto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        return list;
    }

    public int deleteReply(int replyIdx) {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = DbUtil.connect();
        int count = 0;

        try {
            String sql = "DELETE FROM mb_rpy WHERE rpy_idx = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, replyIdx);
            count = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        } finally {
            DbUtil.close(conn, ps);
        }

        return count;
    }

    public int modifyReply(int replyIdx, String content) {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = DbUtil.connect();
        int count = 0;

        try {
            String sql = "UPDATE mb_rpy SET content = ? WHERE rpy_idx = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, content);
            ps.setInt(2, replyIdx);
            count = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        } finally {
            DbUtil.close(conn, ps);
        }

        return count;
    }
}
