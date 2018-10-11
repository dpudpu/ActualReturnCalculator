package my.examples.arc.servlet;

import my.examples.arc.dao.ArcDao;
import my.examples.arc.dao.DbUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/investment/input")
public class ARCInvestWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ARCGdsMstDto> list = new ArrayList<>();
        ArcDao arcDao = new ArcDao();
        list = arcDao.getAllGoodsListDto();
        req.setAttribute("allGoodsList", list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/investinput.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int invPrdIdx = Integer.parseInt(req.getParameter("goodsidx"));
        int invMoney = Integer.parseInt(req.getParameter("mymoney"));
        int invPeriod = Integer.parseInt(req.getParameter("investperiod"));

        ARCInvInputDto arcInvInputDto =
                new ARCInvInputDto(invPrdIdx, invMoney, invPeriod);
        ArcDao arcDao = new ArcDao();
        arcDao.addMyGoodsList(arcInvInputDto);

        resp.sendRedirect("/list");
    }
}
