package com.firstproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstproject.member.db.MemberDAO;
import com.firstproject.member.db.MemberDTO;


public class MemberJoinAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : MemberJoinAction_execute() 호출");

		request.setCharacterEncoding("UTF-8");
		
		MemberDTO mdto = new MemberDTO();
		// mdto.setIdx(Integer.parseInt(request.getParameter("idx"))); - DB auto increment
		mdto.setId(request.getParameter("id"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setName(request.getParameter("name"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setAddress(request.getParameter("address"));
		mdto.setAddress_detail(request.getParameter("address_detail"));
		mdto.setMobile(request.getParameter("mobile"));
		// mdto.setJoinDate(new Date()); -> sql구문으로 추가 
		System.out.println("Model : "+mdto);
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.insertMember(mdto);
		System.out.println("Model : 회원가입 처리 완료");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result <= 0) {
			out.print("<script>");
			out.print("alert('회원 가입에 실패하였습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;	
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);	
		
		if(result > 0) {
			out.print("<script>");
			out.print("alert('회원 가입에 성공하였습니다. 로그인 페이지로 이동합니다.');");
			out.print("location.href='./MemberLogin.me';");
			out.print("</script>");
			out.close();
			return forward;
		}
		return forward;
	}
	
	
}
