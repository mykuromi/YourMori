package com.firstproject.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstproject.member.db.MemberDAO;
import com.firstproject.member.db.MemberDTO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : MemberListAction_execute() 호출 ");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		MemberDAO mdao = new MemberDAO();
		List<MemberDTO> memberList  = mdao.getMemberList();
		request.setAttribute("memberList", memberList);

		forward.setRedirect(false);
		forward.setPath("./member/list.jsp");		
		
		return forward;
	}

}
