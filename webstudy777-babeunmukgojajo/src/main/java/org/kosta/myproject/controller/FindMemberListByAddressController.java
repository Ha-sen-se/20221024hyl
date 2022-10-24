package org.kosta.myproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.myproject.model.MemberDAO;
import org.kosta.myproject.model.MemberVO;

public class FindMemberListByAddressController implements Controller {
	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<MemberVO> list=MemberDAO.getInstance().findMemberListByAddress(request.getParameter("address"));
		request.setAttribute("list", list);
		return "member/findmemberlistbyaddress.jsp";
	}
}
