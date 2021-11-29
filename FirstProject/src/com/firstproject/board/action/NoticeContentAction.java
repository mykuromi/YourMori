package com.firstproject.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstproject.board.db.BoardDAO;
import com.firstproject.board.db.BoardDTO;

public class NoticeContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : BoardContentAction_execute() 호출");

		request.setCharacterEncoding("UTF-8");

		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO bdao = new BoardDAO();
		bdao.updateNoticeReadCount(num);
		BoardDTO bdto = bdao.getNotice(num);
		
		request.setAttribute("bdto", bdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./notice/noticecontent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
