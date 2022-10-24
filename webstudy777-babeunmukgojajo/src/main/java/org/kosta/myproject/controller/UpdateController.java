package org.kosta.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.myproject.model.MemberDAO;
import org.kosta.myproject.model.MemberVO;

public class UpdateController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		MemberVO mvo=(MemberVO) request.getSession(false).getAttribute("member");
//		String id=mvo.getId();
		String id=(String) request.getParameter("memberId");
		String password=(String) request.getParameter("memberPassword");
		String name=(String) request.getParameter("memberName");
		String address=(String) request.getParameter("memberAddress");
		MemberDAO.getInstance().update(id,name,password,address);
		MemberVO mvo=new MemberVO(id, password, name, address);
		request.getSession().setAttribute("member", mvo);
		return "redirect:member/update-result.jsp";
	}
}
