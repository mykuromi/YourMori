package com.firstproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstproject.member.db.MemberDAO;


public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : MemberLoginAction_execute() 호출");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println("Model : id="+id+", pwd="+pwd);

		MemberDAO mdao = new MemberDAO();
		int result = mdao.idCheck(id,pwd);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result == -1) {
			// 	비회원, print("-") -자체가 html영역이므로 <html> 안 씀 
			out.print("<script>");
			out.print("alert('일치하는 아이디가 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;	
		}
		if(result == 0) {
			out.print("<script>");
			out.print("alert('비밀번호가 일치하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;	
		}
		// result == 1 : 정상 처리(로그인)
		HttpSession session = request.getSession();
		session.setAttribute("id", id);

		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);
		
		return forward;
	}

	
	
	
}
