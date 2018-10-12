package my.examples.arc.servlet;

import my.examples.arc.dao.ArcDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reply/modify")
public class ARCReplyModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/replymodify.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 댓글을 작성한 id와 현재 로그인 한 id가 같은 경우
//        this.doGet(req, resp);

        // 댓글을 작성한 id와 현재 로그인 한 id가 다른 경우

        // 댓글 수정시
        int reply_idx = Integer.parseInt(req.getParameter("reply_idx"));
        String content = req.getParameter("reply_content");
        ArcDao arcDao = new ArcDao();
        arcDao.modifyReply(reply_idx, content);

        resp.sendRedirect("/list");
    }
}
