package test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.dao.MovieDao;
import test.vo.MovieVo;

@WebServlet("/main")
public class MainController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 영화목록(MovieDao)을 db에서 얻어와서 request에 담아보세요. -> /5/main.jsp로 이동하는 코드 완성해 보세요
		MovieDao dao=MovieDao.getInstance();
		ArrayList<MovieVo> list=dao.selectList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/3/main.jsp").forward(request, response);






	}
}







