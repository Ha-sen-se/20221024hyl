package org.kosta.myproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestController implements Controller {
	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path="test-result.jsp"; //forward
		//String path="redirect:test-result.jsp"; //redirect
		return path;
	}	
}
