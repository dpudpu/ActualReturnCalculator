package my.examples.arc.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/investment/input")
public class ARCInvestWriteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 아직 상품 목록 불러와서 고르기는 안됨
        String invPrdName = req.getParameter("productname");
        int invMoney = Integer.parseInt(req.getParameter("mymoney"));
        int invPeriod = Integer.parseInt(req.getParameter("investperiod"));

        ARCInvInputDto arcInvInputDto =
                new ARCInvInputDto(invPrdName, invMoney, invPeriod);
        ArcDao arcDao = new ArcDao(arcInvInputDto);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/investinput.jsp");
        resp.sendRedirect("/list");
    }
}
