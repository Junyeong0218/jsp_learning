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
			result = "홀수";
		} else {
			result = "짝수";
		}
		
		request.setAttribute("result", result);
		
		// redirect = 아예 새로운 요청
		// forward = 현재 가진 데이터를 이어받아서 요청
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
}
