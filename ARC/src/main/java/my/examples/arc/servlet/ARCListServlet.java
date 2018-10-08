package my.examples.arc.servlet;

import javafx.scene.shape.Arc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list")
public class ARCListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ArcDto> list = new ArrayList<>();

        list.add(
                new ArcDto(1,"jang", 10000, 12, 100000));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(req, resp);
    }
}
