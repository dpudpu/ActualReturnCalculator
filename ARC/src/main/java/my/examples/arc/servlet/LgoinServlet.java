package my.examples.arc.servlet;

import my.examples.arc.dao.MemberDao;
import my.examples.arc.dto.MemberDto;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LgoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/login.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(req.getParameter("userId"));
        memberDto.setPassword(req.getParameter("userPassword"));

        MemberDao memberDao = new MemberDao();
        if(memberDao.login(memberDto)==1){
            HttpSession session = req.getSession();
            session.setAttribute("user",req.getParameter("userId"));
            resp.sendRedirect("/list");
        }else{

            req.setAttribute("msg","-1");
            req.setAttribute("userId",req.getParameter("userId"));
            req.setAttribute("userPassword",req.getParameter("userPassword"));
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/login.jsp");
            dispatcher.forward(req,resp);
        }
    }
}
