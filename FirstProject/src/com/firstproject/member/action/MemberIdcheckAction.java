package com.firstproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstproject.member.db.MemberDAO;
import com.firstproject.member.db.MemberDTO;

public class MemberIdcheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : MemberIdcheckAction_execute() 호출 ");
		
		String id = (String) request.getParameter("id");
		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto  = mdao.getMember(id);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(mdto != null) {
			out.print("해당 아이디는 중복입니다.");
			out.close();
			return null;
		}
		out.print("사용 가능한 아이디입니다.");
		out.close();
		
		return null;
	}

}
