package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;
import test.vo.CommentsVo;
/*
Connection con=DriverManage.getConnection(..);
con.setAutoCommit(false); //자동트랜잭션 해제
pstmt.executeUpdate();
..
con.commit();//커밋하기
con.rollback();//롤백하기

con.close();
 */
public class CommentsDao {
	//싱글톤 객체로 만들어 보세요.
	private static CommentsDao instance=new CommentsDao();
	private CommentsDao() {}
	public static CommentsDao getInstance() {
		return instance;
	}
	//delete메소드 만들어 보세요 - 트랜잭션 처리 하세요
	public int delete(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=JdbcUtil.getCon();
		try {
			con.setAutoCommit(false);
			String sql="delete from comments where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			int n=pstmt.executeUpdate();
			con.commit();
			return n;		
		}catch(SQLException s) {
			s.printStackTrace();
			try {
				con.rollback();
			}catch(SQLException s1) {
				System.out.println(s1.getMessage());
			}
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int insert(CommentsVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=JdbcUtil.getCon();
		try {		
			con.setAutoCommit(false);//자동커밋 해제하기
			String sql="insert into comments values(comments_seq.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,vo.getMnum());
			pstmt.setString(2,vo.getId());
			pstmt.setString(3,vo.getComments());
			int n=pstmt.executeUpdate();
			con.commit();//커밋하기- db에 반영하기
			return n;		
		}catch(SQLException s) {
			s.printStackTrace();
			try {
				con.rollback();//롤백하기 - 작업 취소하기
			}catch(SQLException e) {
				System.out.println(e);
			}
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int update(int num, String comments) {
		Connection con=null;
		PreparedStatement pstmt=null;
		con=JdbcUtil.getCon();
		try {
			con.setAutoCommit(false);
			String sql = "update comments set comments=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, comments);
			pstmt.setInt(2, num);
			int n = pstmt.executeUpdate();
			con.commit();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				con.rollback();
			}catch (SQLException s1) {
				System.out.println(s1.getMessage());
			}
			return -1;
		} finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}

	public ArrayList<CommentsVo> cList(int mnum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		con=JdbcUtil.getCon();
		try {
			
			String sql="select * from comments where mnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs=pstmt.executeQuery();
			ArrayList<CommentsVo> list=new ArrayList<CommentsVo>();
			while(rs.next()) {
				int num=rs.getInt("num");
				String id=rs.getString("id");
				String comments=rs.getString("comments");
				CommentsVo vo=new CommentsVo(num, mnum, id, comments);
				list.add(vo);
			}
			return list;
		}catch(SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}

}





