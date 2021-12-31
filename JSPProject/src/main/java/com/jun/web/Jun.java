package com.jun.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

@WebServlet("/hello")
public class Jun extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String s_cnt = request.getParameter("cnt");
		
		int cnt = 100;
		
		if(s_cnt != null && !s_cnt.equals("")) {
			cnt = Integer.parseInt(s_cnt);
		}
		
		for(int i=0; i<cnt; i++) {
			out.println((i+1) + ": ¾È³çÇÏ¼¼¿ä. Servlet!!");
		}
	}

}
