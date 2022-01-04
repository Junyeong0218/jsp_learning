package com.jun.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int num = 0;
		String temp = request.getParameter("n");
		
		if(temp != null && !temp.equals("")){
			num = Integer.parseInt(temp);
		}
		
		String result = "";
		if(num%2 == 1){
			result = "Ȧ��";
		} else {
			result = "¦��";
		}
		
		request.setAttribute("result", result);
		
		// redirect = �ƿ� ���ο� ��û
		// forward = ���� ���� �����͸� �̾�޾Ƽ� ��û
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
}
