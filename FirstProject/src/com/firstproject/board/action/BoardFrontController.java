package com.firstproject.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BoardFrontController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Controller : BoardFrontController - doProcess() 호출 ");
		
		
		//-------------------------------------------------------------------
		
		// 1. 가상주소 가져오기 
		System.out.println("Controller : 1. 가상주소 가져오기");
		
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String command = requestURI.substring(ctxPath.length());

		//-------------------------------------------------------------------
			
		// 2. 가상주소 매핑하기 
		System.out.println("Controller : 2. 가상주소 매핑하기");
		Action action = null;
		ActionForward forward = null;
		 
		if(command.equals("/Notice.bo")){	
			System.out.println("Controller : /Notice.bo 호출");
			 action = new NoticeListAction();
			 try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}else if(command.equals("/Gallery.bo")){	
			 action = new GalleryListAction();
			 try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Board.bo")){	
			System.out.println("Controller : /Board.bo 호출");
			forward = new ActionForward();
			forward.setPath("./board/board.jsp");
			forward.setRedirect(false);
			
	
		}else if(command.equals("/NoticeWrite.bo")){
			 System.out.println("Controller : /NoticeWrite.bo 주소 호출");
			 forward = new ActionForward();
			 forward.setPath("./notice/noticewrite.jsp");
			 forward.setRedirect(false);
			 
		}else if(command.equals("/NoticeWriteAction.bo")) {
			 System.out.println("Controller : ./NoticeWriteAction.bo 주소 호출");
			 action = new NoticeWriteAction();
			 try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		}else if(command.equals("/NoticeContent.bo")) {
			System.out.println("Controller : /NoticeContent.bo 주소 호출 ");
			action = new NoticeContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/NoticeSearch.bo")){	
			System.out.println("Controller : /NoticeSearch.bo 호출");
			 action = new NoticeSearchAction();
			 try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if(command.equals("/NoticeUpdate.bo")){
			 System.out.println("Controller : /NoticeUpdate.bo 주소 호출");
			 action = new NoticeUpdateAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 
		}else if(command.equals("/NoticeUpdateProAction.bo")){
			 System.out.println("Controller : /NoticeUpdateProAction.bo 주소 호출");
			 action = new NoticeUpdateProAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 
		}else if(command.equals("/NoticeDelete.bo")){
			 System.out.println("Controller : /NoticeDelete.bo 주소 호출");
			 action = new NoticeDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 
		}else if(command.equals("/NoticeReply.bo")){
			 System.out.println("Controller : /NoticeReply.bo 주소 호출");
			 forward = new ActionForward();
			 forward.setPath("./notice/noticereply.jsp");
			 forward.setRedirect(false);
			 
		}else if(command.equals("/NoticeReplyAction.bo")){
			 System.out.println("Controller : /NoticeReplyAction.bo 주소 호출");
			 action = new NoticeReplyAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 
		}
		 
		 
		 
		 
		 
		//-------------------------------------------------------------------
			
		// 3.  페이지 이동하기 
		if(forward != null){
			System.out.println("Controller : 3. 가상주소 이동하기");
			if(forward.isRedirect()){ // true
				System.out.println("Controller : sendRedirect방식 - "+forward.getPath()+"페이지로 이동");
				response.sendRedirect(forward.getPath());				 
			}else{// false
				System.out.println("Controller : forward방식 - "+forward.getPath()+"페이지로 이동");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);				 
			}
		}
	}

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
