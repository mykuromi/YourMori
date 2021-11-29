package com.firstproject.board.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstproject.board.db.BoardDAO;
import com.firstproject.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : NoticeDeleteAction_execute() 호출");

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
		System.out.println("num : "+num);
		
		
		BoardDAO bdao = new BoardDAO();
		int result = bdao.deleteNotice(num);
		
		String path = request.getSession().getServletContext().getRealPath("/upload");
		int maxSize = 10 * 1024 * 1024; 	// 10MB
		MultipartRequest multi = 
				new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String file = multi.getParameter("file");
		
		if(file != null || !file.equals("")){
			String sDownLoadPath = request.getSession().getServletContext().getRealPath("/upload");
			String sFilePath = sDownLoadPath+"/"+file;
			File f = new File(sFilePath);
			if (f.isFile()){
			f.delete();
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result <= 0){
			out.print("<script>");
			out.print("alert('글이 삭제되지 않았습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		
		//result == 1
		out.print("<script>");
		out.print("alert('글이 정상적으로 삭제되었습니다.'); ");
		out.print("location.href='./Notice.bo';");
		out.print("</script>");
		out.close();
		
		
		return null;
	}

}
