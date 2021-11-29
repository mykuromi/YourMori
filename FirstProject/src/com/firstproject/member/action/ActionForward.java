package com.firstproject.member.action;

public class ActionForward {
	
	// Controller에서 페이지를 이동할 때 이동하는 정보를 저장하는 객체 
	// (페이지 주소, 이동 방식)
	private String path; 
	private boolean isRedirect;
		// true - sendRedirect 방식으로 이동 
		// false - forward 방식으로 이동 
	
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
	@Override
	public String toString() {
		return "ActionForward(페이지이동정보) [path=" + path + ", isRedirect=" + isRedirect + "]";
	} 
	
	
}
