package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;
import test.vo.MovieVo;
// 싱글톤 패턴 객체 만들기 - 객체를 하나만 생성해서 공유해서 사용하는 기법
// 방법
// 1. 자기자신의 객체를 static객체로 생성하기
// 2. 1번의 객체를 리턴하는 메소드 만들기
// 3. 생성자를 private으로 만들기
public class MovieDao {
	private static MovieDao instance=new MovieDao();
	private MovieDao() {}	
	public static MovieDao getInstance() {
		return instance;
	}
	public MovieVo select(int mnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;	
		String sql="select * from movie where mnum=?";
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String title=rs.getString("title");
				String content=rs.getString("content");
				String director=rs.getString("director");
				MovieVo vo=new MovieVo(mnum, title, content, director);
				return vo;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return null;
	}
	public ArrayList<MovieVo> selectList() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;	
		String sql="select * from movie";
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<MovieVo> list=new ArrayList<MovieVo>();
			while(rs.next()) {
				int mnum=rs.getInt("mnum");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String director=rs.getString("director");
				MovieVo vo=new MovieVo(mnum, title, content, director);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return null;
	}
}
