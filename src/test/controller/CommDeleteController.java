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

@WebServlet("/comm/delete")
public class CommDeleteController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int num = Integer.parseInt(req.getParameter("num"));

        CommentsDao dao = CommentsDao.getInstance();
        int delete = dao.delete(num);
        JSONObject json = new JSONObject();
        json.put("list", delete);

        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.println(json);
        pw.close();
    }
}
