package my.examples.arc.servlet;

import my.examples.arc.dao.GoodsPostDao;
import my.examples.arc.dao.InvestListBoardDao;
import my.examples.arc.dto.ARCReplyDto;
import my.examples.arc.dto.MyGoodsListDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/list")
public class ARCListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pg = "1";

        if (req.getParameter("pg") != null && Pattern.matches("^[0-9]*$", req.getParameter("pg"))) {
            pg = req.getParameter("pg");
        }
        System.out.println(req.getParameter("posts"));
        int posts = 5;
        if (req.getParameter("posts") != null && Pattern.matches("^[0-9]*$", req.getParameter("posts"))) {
            posts = Integer.parseInt(req.getParameter("posts"));
        }

        InvestListBoardDao investListBoardDao = new InvestListBoardDao();
        List<MyGoodsListDto> list = investListBoardDao.getMyGoodsList(pg, posts);

        int totalPage = investListBoardDao.getCnt();
        totalPage = (totalPage - 1) / posts + 1;
        // req.setAttribute  request에 list 저장
        req.setAttribute("myGoodsList", list);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("posts", posts);
        req.setAttribute("pg", pg);


        // DAO에 id 입력후 투자 한 상품 목록 출력

        // List에 투자상품 목록 Dto 담기

        // 댓글 목록 보여주기
        GoodsPostDao goodsPostDao = new GoodsPostDao();
        List<ARCReplyDto> arcReplyList = new ArrayList<>();
        arcReplyList = goodsPostDao.getReply();
        req.setAttribute("replylist", arcReplyList);
        req.setAttribute("replysize", arcReplyList.size());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(req, resp);
    }
}
