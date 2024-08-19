package test.controller;

import org.json.JSONObject;
import test.dao.CommentsDao;
import test.vo.CommentsVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/comm/insert")
public class CommInsertController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello");
        int mnum = Integer.parseInt(req.getParameter("mnum"));
        String id = req.getParameter("id");
        String comments = req.getParameter("comments");
        CommentsDao dao = CommentsDao.getInstance();
        int insert = dao.insert(new CommentsVo(0, mnum, id, comments));
        JSONObject json = new JSONObject();
        json.put("list", insert);

        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.println(json);
        pw.close();
    }
}
