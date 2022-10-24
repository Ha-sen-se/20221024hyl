package org.kosta.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.myproject.model.MemberDAO;
import org.kosta.myproject.model.MemberVO;

public class LoginController implements Controller {
	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path=null;
		MemberVO mvo=MemberDAO.getInstance().login(request.getParameter("memberId"), request.getParameter("memberPassword"));
		if(mvo!=null) {
			request.getSession().setAttribute("member", mvo);
			path="redirect:index.jsp";
		} else {
			path="redirect:member/login-fail.jsp";
		}
		return path;
	}
}
