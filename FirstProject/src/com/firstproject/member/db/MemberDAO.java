package com.firstproject.member.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MemberDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	

	
	private Connection getCon() throws Exception{
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/firstproject");
		con = ds.getConnection();
		return con;
	}	//getCon()

	
	
	public void closeDB() {
		try {
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	//closeDB()
	
	
	
	public int insertMember(MemberDTO mdto) {
		int result = 0;
		try {
			con = getCon();
			sql = "insert into first_member(id,pwd,name,email,joindate,address,address_detail,mobile) "
					+ "values(?,?,?,?,now(),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPwd());
			pstmt.setString(3, mdto.getName());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setString(5, mdto.getAddress());
			pstmt.setString(6, mdto.getAddress_detail());
			pstmt.setString(7, mdto.getMobile());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}	// insertMember()
	
	
	
	public int idCheck(String id, String pwd) {
		int result = -1;
		try {
			con = getCon();
			sql = "select pwd from first_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(pwd.equals(rs.getString("pwd"))) {	// 회원 - 비밀번호 일치 
					result = 1;
				} else {	// 회원 - 비밀번호 불일치
					result = 0;
				}
			} else {	// 비회원 
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}	// idCheck()
	
	
	
	public MemberDTO getMember(String id) {
		MemberDTO mdto = null;
		try {
			con = getCon();
			sql = "select * from first_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mdto = new MemberDTO();
				mdto.setIdx(rs.getInt("idx"));
				mdto.setId(rs.getString("id"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setName(rs.getString("name"));
				mdto.setEmail(rs.getString("email"));
				mdto.setJoindate(rs.getDate("joindate"));
				mdto.setAddress(rs.getString("address"));
				mdto.setAddress_detail(rs.getString("address_detail"));
				mdto.setMobile(rs.getString("mobile"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return mdto;
	}	// getMember()
	
	
	
	public int updateMember(MemberDTO mdto){
		int result = -1;
		try {
			con = getCon();
			sql = "select pwd from first_member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(mdto.getPwd().equals(rs.getString("pwd"))){
					sql = "update first_member set name=?,email=?,address=?,"
							+ "address_detail=?,mobile=? where id = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, mdto.getName());
					pstmt.setString(2, mdto.getEmail());
					pstmt.setString(3, mdto.getAddress());
					pstmt.setString(4, mdto.getAddress_detail());
					pstmt.setString(5, mdto.getMobile());
					pstmt.setString(6, mdto.getId());
					result = pstmt.executeUpdate();
				}else{	// 비밀번호 오류
					result = 0;
				}
			}else{	// 아이디 없음
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}	// updateMember()
	
	
	
	public int deleteMember(String id, String pwd){
		int result = -1;
		try {
			con = getCon();
			sql = "select pwd from first_member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(pwd.equals(rs.getString("pwd"))){
					sql = "delete from first_member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					result = pstmt.executeUpdate();
				}else{	// 비밀번호 오류
					result = 0;
				}
			}else{	// 아이디 없음
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}	// deleteMember()	
	
	
	
	public List<MemberDTO> getMemberList(){
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			con = getCon();
			sql = "select * from first_member where id != ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "admin");
			rs = pstmt.executeQuery();
			while(rs.next()){
				MemberDTO mdto = new MemberDTO();
				mdto.setIdx(rs.getInt("idx"));
				mdto.setId(rs.getString("id"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setName(rs.getString("name"));
				mdto.setEmail(rs.getString("email"));
				mdto.setJoindate(rs.getDate("joindate"));
				mdto.setAddress(rs.getString("address"));
				mdto.setAddress_detail(rs.getString("address_detail"));
				mdto.setMobile(rs.getString("mobile"));
				memberList.add(mdto);						
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return memberList;
	}	// getMemberList()
	
	
}
