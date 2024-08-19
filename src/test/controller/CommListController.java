package test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.dao.CommentsDao;
import test.vo.CommentsVo;

@WebServlet("/comm/list")
public class CommListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int mnum=Integer.parseInt(req.getParameter("mnum"));
		CommentsDao dao=CommentsDao.getInstance();
		ArrayList<CommentsVo> list=dao.cList(mnum);

//		JSONArray arr=new JSONArray();
//		arr.put(list);

		JSONObject result=new JSONObject();
		result.put("list", list);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.println(result);
		pw.close();
	}
}