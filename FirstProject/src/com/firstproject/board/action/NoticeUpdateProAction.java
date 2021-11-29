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

public class NoticeUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : NoticeUpdateProAction_execute() 호출");

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
		String file = multi.getFilesystemName("file");
		
		if(file == null){
			file = multi.getParameter("oldfile");
		}else{
			String fileName = multi.getParameter("oldfile");
			String sDownLoadPath = request.getSession().getServletContext().getRealPath("/upload");
			String sFilePath = sDownLoadPath+"/"+fileName;
			File f = new File(sFilePath);
			if (f.isFile()){
			f.delete();
			}
		}
		
		int num = Integer.parseInt(multi.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println(pageNum);
		
		BoardDTO bdto = new BoardDTO();
		bdto.setNum(num);
		bdto.setName(multi.getParameter("name"));
		bdto.setFile(file);
		bdto.setSubject(multi.getParameter("subject"));
		bdto.setContent(multi.getParameter("content"));
		
		BoardDAO bdao = new BoardDAO();
		int result = bdao.updateNotice(bdto);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result <= 0){
			out.print("<script>");
			out.print("alert('글이 수정되지 않았습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		
		//result == 1
		out.print("<script>");
		out.print("alert('글이 정상적으로 수정되었습니다.'); ");
		out.print("location.href='./NoticeContent.bo?num="+num+"&pageNum="+pageNum+"';");
		out.print("</script>");
		out.close();
		return null;
	}

}
