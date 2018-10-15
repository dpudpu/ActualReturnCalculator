package my.examples.arc.servlet;

import my.examples.arc.dao.GoodsPostDao;

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
        req.setCharacterEncoding("UTF-8");
        // 댓글을 작성한 id와 현재 로그인 한 id가 같은 경우
//        this.doGet(req, resp);

        // 댓글을 작성한 id와 현재 로그인 한 id가 다른 경우

        // 댓글 수정시
        // 로그인 연동이 안되어 임시로 1번만 수정이 가능하도록 수정
//        int reply_idx = Integer.parseInt(req.getParameter("reply_idx"));
        int reply_idx = 1;
        String content = req.getParameter("reply_content");
        GoodsPostDao goodsPostDao = new GoodsPostDao();
        goodsPostDao.modifyReply(reply_idx, content);

        resp.sendRedirect("/list");
    }
}
