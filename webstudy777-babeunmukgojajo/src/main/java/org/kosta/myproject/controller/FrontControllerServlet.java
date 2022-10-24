package org.kosta.myproject.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Front Controller Design Pattern : 클라이언트의 요청을 하나의 진입점으로 모아서 처리 (공통정책:인코딩, 예외처리, 인증 등..)을 효과적으로 처리할 수 있다. (예 : 은행 콜센터, 키오스크)
 * 
 */
@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{			
			String servletPath=request.getServletPath();
			String controllerName=servletPath.substring(1, servletPath.lastIndexOf("."));
			//System.out.println(getServletName()+" Servlet path: "+servletPath);
			//System.out.println(getServletName()+" Controller Name: "+controllerName);
			Controller controller=HandlerMapping.getInstance().create(controllerName);
			//System.out.println(getServletName()+"개별 컨트롤러 구현체"+controller);
			//Controller interface로 캡슐화, 계층구조 형성하였으므로 다양한 하위 컨트롤러 객체들을 단일한 소통방식으로 제어할 수 있다. -> 다형성 지원
			String viewPath=controller.handlerRequest(request,response);
			if(viewPath.startsWith("redirect:")){ // redirect 방식으로 응답 (기존 request와 response 유지하지 않고 새로 생성)
				response.sendRedirect(viewPath.trim().substring(9));
			}else { // forward 방식으로 응답(기존 request&response 유지)
				request.getRequestDispatcher(viewPath).forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDispatch(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doDispatch(request,response);
	}


}
