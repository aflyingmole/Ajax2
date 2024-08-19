package test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.vo.Member;

@WebServlet("/mem/info")
public class MemberInfoController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member vo=new Member("hello","1234","hello@",10,LocalDateTime.now());
		JSONObject json=new JSONObject(vo);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		pw.close();
	}
}








