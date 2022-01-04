package com.jun.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoGetDoPost extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// request.getMethod();
		// == 어떤 요청이 왔는지 String 으로 반환
		// GET, POST
		
		// super(request, response);
		// HttpServlet(request, response);
		
		// get 요청과 post 요청을 동시에 처리해야할 경우
		// service 메소드를 override 해서 처리
		// ( 이 경우 부모클래스를 재호출 하지않기 위해 super(request, response); 지우기
		
		// get 요청만 처리하면 되는 경우 service 메소드 대신에 doGet 메소드를
		// post 요청만 처리하면 되는 경우 service 메소드 대신에 doPost 메소드를
		// override 해서 처리
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
