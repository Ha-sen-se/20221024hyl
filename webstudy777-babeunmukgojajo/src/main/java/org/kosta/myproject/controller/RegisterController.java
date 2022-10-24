package org.kosta.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.myproject.model.MemberDAO;
import org.kosta.myproject.model.MemberVO;

public class RegisterController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path=null;
		String id=request.getParameter("memberId");
		String pw=request.getParameter("memberPassword");
		String name=request.getParameter("memberName");
		String address=request.getParameter("memberAddress");
		MemberDAO.getInstance().register(new MemberVO(id,pw,name,address));
		path="redirect:member/register-result.jsp";
		return path;
	}
}
