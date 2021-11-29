package com.firstproject.board.action;

public class ActionForward {

	// 컨트롤러에서 페이지 이동할때 이동하는 정보를 저장하는 객체
	// (페이지 주소,이동 방식)
	
	private String path; // 주소
	private boolean isRedirect; // 방식
	        //  true - sendRedirect 방식으로 이동한다.
	        //  false - forward 방식으로 이동한다.	

	//  alt shift s + r
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	// alt shift s + s
	@Override
	public String toString() {
		return "ActionForward(페이지이동정보) [path=" + path 
				             + ", isRedirect=" + isRedirect + "]";
	}
	
	
}
