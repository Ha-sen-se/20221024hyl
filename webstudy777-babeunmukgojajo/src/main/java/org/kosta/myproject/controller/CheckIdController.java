package org.kosta.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.myproject.model.MemberDAO;

public class CheckIdController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		boolean checkId=MemberDAO.getInstance().checkId(id);
		String message=null;
		if(checkId) {
			message="ok";
		} else {
			message="fail";
		}
		request.setAttribute("responsebody", message); // AjaxViewServlet이 클라이언트에게 응답하도록 저장
		return "AjaxView";
	}

}
