package com.firstproject.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BoardDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	
	
	public Connection getCon() throws Exception{
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/firstproject");
		con = ds.getConnection();
		System.out.println("DAO : DB 연결 성공 - "+con);
		return con;
	}	// getCon();
	


	public void closeDB() {
		try {
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	// closeDB()
	
	
	
	public void updateNoticeReadCount(int num) {
		try {
			con = getCon();
			sql = "update notice_board set readcount=readcount+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			System.out.println("DAO : 글 조회수 1 증가 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}	// updateReadCount()
	
	
	
	public int getNoticeCount() {
		int cnt = 0;
		try {
			con = getCon();
			sql = "select count(*) from notice_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			System.out.println("DAO : 저장된 글의 개수 - "+cnt+"개");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return cnt;
	}	// getNoticeCount()

	
	
	public int getNoticeCount(String search){
		int cnt = 0;
		try {
			con = getCon();
			sql = "select count(num) from notice_board where subject like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}
			System.out.println("DAO : 검색 글 개수 - "+cnt+"개");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return cnt;
	}	// getNoticeCount(String)
	
	
	
	public List<BoardDTO> getNoticeList(int startRow, int pageSize) {
		List<BoardDTO> noticeList = new ArrayList<BoardDTO>();
		try {
			con = getCon();
			sql = "select * from notice_board order by re_ref desc, re_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO bdto = new BoardDTO();
				bdto.setNum(rs.getInt("num"));
				bdto.setName(rs.getString("name"));
				bdto.setPass(rs.getString("pass"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setRe_ref(rs.getInt("re_ref"));
				bdto.setRe_lev(rs.getInt("re_lev"));
				bdto.setRe_seq(rs.getInt("re_seq"));
				bdto.setDate(rs.getDate("date"));
				bdto.setIp(rs.getString("ip"));
				bdto.setFile(rs.getString("file"));
				noticeList.add(bdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return noticeList;
	}	// getNoticeList()
		
	
	
	public List<BoardDTO> getNoticeList(int startRow,int pageSize,String search){
		List<BoardDTO> noticeList = new ArrayList<BoardDTO>();
		try {
			con=getCon();
			sql="select * from notice_board where subject like ? order by re_ref desc, re_seq asc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%"); //검색어 포함
			pstmt.setInt(2, startRow-1); 
			pstmt.setInt(3, pageSize);
			rs=pstmt.executeQuery();
			while(rs.next()){
				BoardDTO bdto = new BoardDTO();
				bdto.setNum(rs.getInt("num"));
				bdto.setName(rs.getString("name"));
				bdto.setPass(rs.getString("pass"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setRe_lev(rs.getInt("re_lev"));
				bdto.setRe_ref(rs.getInt("re_ref"));
				bdto.setRe_seq(rs.getInt("re_seq"));
				bdto.setDate(rs.getDate("date"));
				bdto.setIp(rs.getString("ip"));
				bdto.setFile(rs.getString("file"));
				noticeList.add(bdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return noticeList;
	}	// getBoardList(search)
	
	
	
	public BoardDTO getNotice(int num) {
		BoardDTO bdto = null;
		try {
			con = getCon();
			sql = "select * from notice_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bdto = new BoardDTO();
				bdto.setNum(rs.getInt("num"));
				bdto.setName(rs.getString("name"));
				bdto.setPass(rs.getString("pass"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setRe_ref(rs.getInt("re_ref"));
				bdto.setRe_lev(rs.getInt("re_lev"));
				bdto.setRe_seq(rs.getInt("re_seq"));
				bdto.setDate(rs.getDate("date"));
				bdto.setIp(rs.getString("ip"));
				bdto.setFile(rs.getString("file"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return bdto;
	}	// getNotice()	
	
	
	public void insertNotice(BoardDTO bdto) {
		int bno = 0;
		try {
			con = getCon();
			sql = "select max(num) from notice_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				bno = rs.getInt(1) + 1;
			}
			sql = "insert into notice_board(num,name,pass,subject,content,"
					+ "readcount,re_ref,re_lev,re_seq,date,ip,file) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, bdto.getName());
			pstmt.setString(3, bdto.getPass());
			pstmt.setString(4, bdto.getSubject());
			pstmt.setString(5, bdto.getContent());
			pstmt.setInt(6, 0);		// readcount 조회수 - 0
			pstmt.setInt(7, bno); 	// re_ref 답글 - 그룹번호(일반글은 글 번호와 동일)
			pstmt.setInt(8, 0);		// re_lev 답글 - 들여쓰기(일반글 0)
			pstmt.setInt(9, 0);		// re_seq 답글 - 순서(일반글 0)
			pstmt.setString(10, bdto.getIp());
			pstmt.setString(11, bdto.getFile());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}	
	}	// insertNotice()
	
	
	
	public int updateNotice(BoardDTO bdto){
		int result = -1;
		try {
			con = getCon();
			sql = "select * from notice_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sql = "update notice_board set subject=?,content=?,file=? where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bdto.getSubject());
				pstmt.setString(2, bdto.getContent());
				pstmt.setString(3, bdto.getFile());
				pstmt.setInt(4, bdto.getNum());
				result = pstmt.executeUpdate();
			}else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return result;
	}	// updateNotice()
	
	
	
	public int deleteNotice(int num){
		int result = -1;
		try {
			con = getCon();
			sql = "select * from notice_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sql = "delete from notice_board where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				result = pstmt.executeUpdate();
			}else{
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return result;
	}	// deleteBoard()
	
	
	
	public void reInsertNotice(BoardDTO bdto) {
		int num = 0;
		try {
			con = getCon();
			sql = "select max(num) from notice_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}
			System.out.println("DAO 답글의 글 번호 : "+num);
			sql = "update notice_board set re_seq = re_seq+1 where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getRe_ref());
			pstmt.setInt(2, bdto.getRe_seq());
			pstmt.executeUpdate();
			sql = "insert into notice_board(num,name,pass,subject,content,"
					+ "readcount,re_ref,re_lev,re_seq,date,ip,file) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);						// 답글 번호
			pstmt.setString(2, bdto.getName());
			pstmt.setString(3, bdto.getPass());
			pstmt.setString(4, bdto.getSubject());
			pstmt.setString(5,bdto.getContent());
			pstmt.setInt(6,0); 							// readcount : 조회수 = 0
			pstmt.setInt(7,bdto.getRe_ref());		// re_ref : 일반글(원글)의 ref값 저장 
			pstmt.setInt(8, bdto.getRe_lev()+1);	// re_lev : 기존의 값(원글) + 1
			pstmt.setInt(9, bdto.getRe_seq()+1);	// re_seq : 기존의 값(원글) + 1 
			pstmt.setString(10, bdto.getIp());
			pstmt.setString(11, bdto.getFile());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} 	// reInsertNotice()
		
	
	
	
	

}
