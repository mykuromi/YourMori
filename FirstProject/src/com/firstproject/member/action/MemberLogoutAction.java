package com.firstproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : MemberLogoutAction_execute() 호출");
		
		// 로그아웃 처리 = 세션 정보 초기화 
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('안전하게 로그아웃 되었습니다.');");
		out.print("location.href='./Main.me';");
		out.print("</script>");
		out.close();
		return null;	
	}

}
