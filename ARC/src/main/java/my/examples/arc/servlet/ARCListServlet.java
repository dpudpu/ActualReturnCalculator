package my.examples.arc.servlet;

import my.examples.arc.dao.ArcDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ARCListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pg = "1";
        if(req.getParameter("pg")!=null) {
            pg = req.getParameter("pg");
        }

        ArcDao arcDao = new ArcDao();
        List<MyGoodsListDto> list = arcDao.getMyGoodsListDto(pg);

        int page = arcDao.getCnt();
        page = (page-1)/5+1;
        System.out.println(page);
        // req.setAttribute  request에 list 저장
        req.setAttribute("myGoodsList", list);
        req.setAttribute("page",page);


        // DAO에 id 입력후 투자 한 상품 목록 출력

        // List에 투자상품 목록 Dto 담기

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(req, resp);
    }
}
