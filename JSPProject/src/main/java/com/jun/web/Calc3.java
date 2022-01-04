package com.jun.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		String temp = request.getParameter("value");
		String operator = request.getParameter("operator");
		
		int value = 0;
		
		if(!isEmpty(temp)) {
			value = Integer.parseInt(temp);
		}
		
		PrintWriter out = response.getWriter();
		
		int result = 0;
		
		if(operator.equals("=")) {
			
			//int x = (Integer)application.getAttribute("value");
			
			//int x = (Integer)session.getAttribute("value");
			
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			int y = value;
			
			//String op = (String)application.getAttribute("operator");
			
			//String op = (String)session.getAttribute("operator");
			
			String op = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("operator")) {
					op = c.getValue();
					break;
				}
			}
			
			if(op.equals("+")) {
				result = x + y;
			} else if(op.equals("-")) {
				result = x - y;
			}
			
			out.print(x + "...." + y + "...." + op + "....");
			out.print(result);
			
		} else {
			//session.setAttribute("value", value);
			//session.setAttribute("operator", operator);
			
			//application.setAttribute("value", value);
			//application.setAttribute("operator", operator);
			
			Cookie valueCookie = new Cookie("value", Integer.toString(value));
			Cookie opCookie = new Cookie("operator", operator);
			
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");
		}
	}
	
	protected boolean isEmpty(String str) {
		if(str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
