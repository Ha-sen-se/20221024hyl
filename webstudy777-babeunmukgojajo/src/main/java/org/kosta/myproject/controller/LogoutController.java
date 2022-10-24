package org.kosta.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path=null;
		HttpSession session=request.getSession(false);
		if(session!=null) {
			session.invalidate();
			path="redirect:index.jsp";
		}
		return path;
	}
}
