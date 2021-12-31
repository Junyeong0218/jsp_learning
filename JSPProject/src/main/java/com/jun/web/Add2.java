package com.jun.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] tempNum = request.getParameterValues("num");
		
		int result = 0;
		
		for(int i=0; i<tempNum.length; i++) {
			
			if(!isEmpty(tempNum[i])) {
				int num = Integer.parseInt(tempNum[i]);
				result += num;
			}
		}
		
		PrintWriter out = response.getWriter();
		
		out.print(result);
	}
	
	protected boolean isEmpty(String str) {
		if(str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
