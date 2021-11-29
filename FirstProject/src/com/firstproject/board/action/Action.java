package com.firstproject.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	// 컨트롤러에서 DB 연결시 사용하는 메서드
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
}
