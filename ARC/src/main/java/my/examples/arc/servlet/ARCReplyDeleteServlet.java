package my.examples.arc.servlet;

import my.examples.arc.dao.ArcDao;
import my.examples.arc.dto.ARCReplyDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reply/delete")
public class ARCReplyDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 삭제하려는 댓글의 id와 현재 로그인 한 id가 일치하는 경우
        int reply_idx =  Integer.parseInt(req.getParameter("reply_idx"));
        ArcDao arcDao = new ArcDao();
        arcDao.deleteReply(reply_idx);

        resp.sendRedirect("/list");

        // 삭제하려는 댓글의 id와 현재 로그인 한 id가 일치하지 않을 경우
//        this.doGet(req, resp);
    }
}
