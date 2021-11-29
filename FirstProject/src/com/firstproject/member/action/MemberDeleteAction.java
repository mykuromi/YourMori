package com.firstproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstproject.member.db.MemberDAO;
import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("Model : MemberDeleteAction_execute 호출 ");
		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// 전달된 정보 저장(pwd) 
		String pwd = request.getParameter("pwd");
		
		// DAO 객체 생성 -> deleteMember(id,pwd)
		MemberDAO mdao = new MemberDAO();
		int result = mdao.deleteMember(id,pwd);
		// 처리 결과에 따른 페이지 이동 (-1 0 1) (js)
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result == 0){
			out.print("<script>");
			out.print("alert('비밀번호 오류입니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}else if(result == -1){
			out.print("<script>");
			out.print("alert('삭제할 아이디가 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		
		//result == 1
		// 탈퇴성공 -> 세션정보 초기화
		session.invalidate();
		
		out.print("<script>");
		out.print("alert('회원 정보가 정상적으로 삭제되었습니다.');");
		out.print("location.href='./Main.me';");
		out.print("</script>");
		out.close();
		return null;
	}

}
