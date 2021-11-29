package com.firstproject.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstproject.board.db.BoardDAO;
import com.firstproject.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class NoticeWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : NoticeWriteAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		String path = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("실제 서버 파일 저장 위치(uploadPath) : "+path);
		System.out.println(path);
		int maxSize = 10 * 1024 * 1024; 	// 10MB
		MultipartRequest multi = 
				new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		BoardDTO bdto = new BoardDTO();
		
		bdto.setName(multi.getParameter("name"));
		bdto.setPass(multi.getParameter("pass"));
		bdto.setFile(multi.getFilesystemName("file"));
		bdto.setSubject(multi.getParameter("subject"));
		bdto.setContent(multi.getParameter("content"));
		bdto.setIp(request.getRemoteAddr());
		System.out.println("Model : "+bdto);
		
		BoardDAO bdao = new BoardDAO();
		bdao.insertNotice(bdto);		

		forward.setPath("./Notice.bo");
		forward.setRedirect(true);
		return forward;
	}

}
