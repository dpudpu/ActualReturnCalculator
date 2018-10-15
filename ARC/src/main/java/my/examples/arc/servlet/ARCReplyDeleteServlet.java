package my.examples.arc.servlet;

import my.examples.arc.dao.GoodsPostDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reply/delete")
public class ARCReplyDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 삭제하려는 댓글의 id와 현재 로그인 한 id가 일치하는 경우
        int replyIdx =  Integer.parseInt(req.getParameter("replyIdx"));
        GoodsPostDao goodsPostDao = new GoodsPostDao();
        try {
            goodsPostDao.deleteReply(replyIdx);
        } catch (RuntimeException re) {
            resp.sendRedirect("error.jsp");
        }

        resp.sendRedirect("/list");

        // 삭제하려는 댓글의 id와 현재 로그인 한 id가 일치하지 않을 경우
//        this.doGet(req, resp);
    }
}
