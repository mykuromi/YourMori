package com.firstproject.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstproject.board.db.BoardDAO;
import com.firstproject.board.db.BoardDTO;

public class NoticeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : NoticeUpdateAction_execute() 호출");

		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO bdao = new BoardDAO();
		BoardDTO bdto = bdao.getNotice(num);
		
		request.setAttribute("bdto", bdto);
		
		forward.setPath("./notice/noticeupdate.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
