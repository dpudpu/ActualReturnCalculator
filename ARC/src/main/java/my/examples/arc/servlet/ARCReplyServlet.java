package my.examples.arc.servlet;

import my.examples.arc.dao.GoodsPostDao;
import my.examples.arc.dto.ARCReplyDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reply")
public class ARCReplyServlet extends HttpServlet {
    // 댓글 추가 / 수정 / 삭제
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodsPostDao goodsPostDao = new GoodsPostDao();
        req.setCharacterEncoding("UTF-8");
        String content = req.getParameter("replyContent");
        try {
            if (!content.isEmpty()) {
                // 아직 로그인 기능 구현이 되지 않아 member_idx = 1로 고정
                // 아직 답 댓글 기능 구현이 되지 않아 parent_idx = null로 고정
//                ARCReplyDto arcReplyDto
//                        = new ARCReplyDto(1, null, content);
                ARCReplyDto arcReplyDto
                        = new ARCReplyDto(1, content);
                goodsPostDao.addReply(arcReplyDto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            PrintWriter out = resp.getWriter();
            out.println("<script language='javascript'>");
            out.println("alert('댓글을 불러오는 도중 오류가 발생하였습니다.');");
            out.println("window.location.href = \"/\";");
            out.println("</script>");
            out.close();
        }
        resp.sendRedirect("/list");
    }
}
