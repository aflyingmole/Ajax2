package test.controller;

import org.json.JSONObject;
import test.dao.CommentsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/comm/update")
public class CommUpdateController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int num = Integer.parseInt(req.getParameter("num"));
        String comments = req.getParameter("comments");

        CommentsDao dao = CommentsDao.getInstance();
        int update = dao.update(num, comments);
        JSONObject json = new JSONObject();
        json.put("list", update);

        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.println(json);
        pw.close();
    }
}
