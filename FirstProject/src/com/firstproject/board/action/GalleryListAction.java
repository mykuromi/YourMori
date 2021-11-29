package com.firstproject.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstproject.board.db.BoardDAO;
import com.firstproject.board.db.BoardDTO;


public class GalleryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Model : GalleryListAction_execute() 호출");
		
		BoardDAO bdao = new BoardDAO();
		int cnt = bdao.getNoticeCount();
		System.out.println("Model : 글 개수 - "+cnt+"개");
		
		// 페이징 처리 
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {pageNum = "1";}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		List<BoardDTO> noticeList = null;
		if(cnt != 0) {
			noticeList = bdao.getNoticeList(startRow, pageSize);
		}
		
		int pageCount = cnt / pageSize + (cnt % pageSize == 0? 0: 1);		
		int pageBlock = 10;
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) {endPage = pageCount;}
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./gallery/gallery.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
