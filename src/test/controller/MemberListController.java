package test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.vo.Member;

@WebServlet("/mem/list")
public class MemberListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member vo=new Member("test", "1234", "1111@", 10, LocalDateTime.now());
		JSONObject jsob=new JSONObject(vo);
		JSONObject jsob1=new JSONObject(new Member("aaa", "aaaa", "aaaa@", 10, LocalDateTime.now()));
		JSONObject jsob2=new JSONObject(new Member("bbb", "bbb", "bbb@", 10, LocalDateTime.now()));
		//json배열에 json객체 담기
		JSONArray array=new JSONArray();
		array.put(jsob);
		array.put(jsob1);
		array.put(jsob2);
		//응답하기위한 json객체
		JSONObject result=new JSONObject();
		result.put("list",array);
		//json으로 응답하기
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw= resp.getWriter();
		pw.println(result);
		pw.flush();
		pw.close();
		
	}
}














