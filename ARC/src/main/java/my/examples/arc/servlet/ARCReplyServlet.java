package my.examples.arc.servlet;

import my.examples.arc.dao.ArcDao;
import my.examples.arc.dto.ARCReplyDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reply")
public class ARCReplyServlet extends HttpServlet {
    // 댓글 목록 보여주기
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    // 댓글 추가 / 수정 / 삭제
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArcDao arcDao = new ArcDao();
        String content = req.getParameter("reply_content");
        try {
            if (!content.isEmpty()) {
                // 아직 로그인 기능 구현이 되지 않아 member_idx = 1로 고정
                ARCReplyDto arcReplyDto
                        = new ARCReplyDto(1, null, content);
                arcDao.addReply(arcReplyDto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        resp.sendRedirect("/list");
    }
}
