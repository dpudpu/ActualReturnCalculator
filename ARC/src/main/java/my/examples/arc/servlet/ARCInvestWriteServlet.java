package my.examples.arc.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import my.examples.arc.dao.GoodsPostDao;
import my.examples.arc.dao.InvestListBoardDao;
import my.examples.arc.dto.ARCGdsMstDto;
import my.examples.arc.dto.ARCInvInputDto;

@WebServlet("/investment/input")
public class ARCInvestWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ARCGdsMstDto> list = new ArrayList<>();
        InvestListBoardDao investListBoardDao = new InvestListBoardDao();
        list = investListBoardDao.getAllGoodsList();
        req.setAttribute("allGoodsList", list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/investinput.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int invPrdIdx = Integer.parseInt(req.getParameter("goodsidx"));
            int invMoney = Integer.parseInt(req.getParameter("mymoney"));
            int invPeriod = Integer.parseInt(req.getParameter("investperiod"));

            ARCInvInputDto arcInvInputDto =
                    new ARCInvInputDto(invPrdIdx, invMoney, invPeriod);

            GoodsPostDao goodsPostDao = new GoodsPostDao();
            try {
                goodsPostDao.addMyGoodsList(arcInvInputDto);
            } catch (RuntimeException re) {
                resp.sendRedirect("error.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendRedirect("error.jsp");
        }

        resp.sendRedirect("/list");
    }
}
