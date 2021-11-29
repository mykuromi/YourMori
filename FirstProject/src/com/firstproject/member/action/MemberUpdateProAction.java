package com.firstproject.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstproject.member.db.MemberDAO;
import com.firstproject.member.db.MemberDTO;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Model : MemberUpdateProAction_execute() 호출");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		MemberDTO mdto = new MemberDTO();
		mdto.setId(request.getParameter("id"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setName(request.getParameter("name"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setAddress(request.getParameter("address"));
		mdto.setAddress_detail(request.getParameter("address_detail"));
		mdto.setMobile(request.getParameter("mobile"));
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.updateMember(mdto);
			
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result == 0){
			out.print("<script>");
			out.print(" alert('비밀번호가 일치하지 않습니다.'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			return null;
		}else if(result == -1){
			out.print("<script>");
			out.print(" alert('수정할 아이디가 없습니다.'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			return null;
		}
		
		//result == 1
		out.print("<script>");
		out.print(" alert('회원정보가 정상적으로 수정되었습니다.'); ");
		out.print(" location.href='./Main.me'; ");
		out.print("</script>");
		out.close();
		return null;
	}

}
