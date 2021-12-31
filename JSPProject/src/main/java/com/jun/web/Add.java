package com.jun.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tempX = request.getParameter("x");
		String tempY = request.getParameter("y");
		
		int x = 0;
		int y = 0;
		
		if(!isEmpty(tempX)) {
			x = Integer.parseInt(tempX);
		}
		
		if(!isEmpty(tempY)) {
			y = Integer.parseInt(tempY);
		}
		
		PrintWriter out = response.getWriter();
		
		out.print(x+y);
	}
	
	protected boolean isEmpty(String str) {
		if(str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
