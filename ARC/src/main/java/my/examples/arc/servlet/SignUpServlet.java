package my.examples.arc.servlet;

import my.examples.arc.dao.MemberDao;
import my.examples.arc.dto.MemberDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/signUp.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(req.getParameter("userId"));
        memberDto.setPassword(req.getParameter("userPassword"));
        memberDto.setName(req.getParameter("userName"));
        memberDto.setEmail(req.getParameter("uesrEmail"));

        MemberDao memberDao = new MemberDao();
        int result = memberDao.signUp(memberDto);
    }
}
