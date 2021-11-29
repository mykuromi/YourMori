package com.firstproject.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemberFrontController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//-------------------------------------------------------------------
		
		// 1. 가상주소 가져오기 
		System.out.println("Controller : 1. 가상주소 가져오기");
	
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String command = requestURI.substring(ctxPath.length());	// length : 7
		
		//-------------------------------------------------------------------
		
		// 2. 가상주소 매핑하기 
		System.out.println("Controller : 2. 가상주소 매핑하기");
		
		// 페이지 주소, 이동 방법을 저장할 객체 
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Main.me")) {	
			System.out.println("Controller : /Main.me 호출");
			forward = new ActionForward();
			forward.setPath("./main/main.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/AboutUs.me")) {	
			System.out.println("Controller : /Main.me 호출");
			forward = new ActionForward();
			forward.setPath("./aboutus/welcome.jsp");
			forward.setRedirect(false);
		
		}else if(command.equals("/MemberJoin.me")) {
			System.out.println("Controller : /MemberJoin.me 주소 호출");
			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberJoinAction.me")) {
			System.out.println("Controller : /MemberJoinAction.me 주소 호출");
			action = new MemberJoinAction();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if(command.equals("/MemberIdcheckAction.me")){
			 System.out.println("Controller : /MemberIdcheckAction.me 호출");
			 action = new MemberIdcheckAction();
			 try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if(command.equals("/MemberLogin.me")){
			System.out.println("Controller : /MemberLoing.me 주소 호출");
			forward = new ActionForward();
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);
		
		}else if(command.equals("/MemberLoginAction.me")) {
			System.out.println("Controller : /MemberLoginAction.me 주소 호출");
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberLogoutAction.me")) {
			System.out.println("Controller : /MemberLogoutAction.me 주소 호출");
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberUpdate.me")) {
			System.out.println("Controller : /MemberUpdate.me 주소 호출");
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberUpdateAction.me")){
			 System.out.println("Controller : /MemberUpdateAction.me 주소 호출 ");
			 action = new MemberUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		 }else if(command.equals("/MemberDelete.me")){
			 System.out.println("Controller : /MemberDelete.me 호출 ");
			 forward = new ActionForward();
			 forward.setPath("./member/delete.jsp");
			 forward.setRedirect(false);
			 
		 }else if(command.equals("/MemberDeleteAction.me")){
			 System.out.println("Controller : /MemberDeleteAction.me 호출");
			 action = new MemberDeleteAction();
			 try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		 }else if(command.equals("/MemberList.me")){
			 System.out.println(" C : /MemberList.me 호출 ");
			 action = new MemberListAction();
			 try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		 }	 
		
		//-------------------------------------------------------------------
		
		// 3.  페이지 이동하기 
		if(forward != null) {
		// 매핑된 주소(페이지 이동정보)가 있는 경우 
			System.out.println("Controller : 3. 가상주소 이동하기");

			// 페이지 이동방식
			if(forward.isRedirect()) {	// true 
				System.out.println("Controller : sendRedirect방식 - "+forward.getPath()+"페이지로 이동");
				response.sendRedirect(forward.getPath());
			}else {		// false 
				System.out.println("Controller : forward방식 - "+forward.getPath()+"페이지로 이동");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
			
		}
	}	// doProcess()
		
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
}
