package org.kosta.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.myproject.model.MemberDAO;
import org.kosta.myproject.model.MemberVO;

public class FindMemberByIdController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path=null;
		MemberVO mvo=MemberDAO.getInstance().findMemberById(request.getParameter("memberId"));
		if(mvo!=null) {
			request.setAttribute("mvo", mvo);
			path="member/findbyid-ok.jsp";
		} else {
			path="member/findbyid-fail.jsp";			
		}
		return path;
	}

}
