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
		// == � ��û�� �Դ��� String ���� ��ȯ
		// GET, POST
		
		// super(request, response);
		// HttpServlet(request, response);
		
		// get ��û�� post ��û�� ���ÿ� ó���ؾ��� ���
		// service �޼ҵ带 override �ؼ� ó��
		// ( �� ��� �θ�Ŭ������ ��ȣ�� �����ʱ� ���� super(request, response); �����
		
		// get ��û�� ó���ϸ� �Ǵ� ��� service �޼ҵ� ��ſ� doGet �޼ҵ带
		// post ��û�� ó���ϸ� �Ǵ� ��� service �޼ҵ� ��ſ� doPost �޼ҵ带
		// override �ؼ� ó��
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
