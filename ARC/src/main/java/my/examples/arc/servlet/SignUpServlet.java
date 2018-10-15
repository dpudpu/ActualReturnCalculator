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
import java.io.PrintWriter;

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
        memberDto.setEmail(req.getParameter("userEmail"));

        MemberDao memberDao = new MemberDao();
        try {
            if (memberDao.signUp(memberDto)==0)
                throw new Exception();
        }catch(Exception e){
            e.printStackTrace();
            PrintWriter out = resp.getWriter();
            out.println("회원가입 실패");
            out.close();
        }

        resp.sendRedirect("/list");
    }
}
