<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String s_cnt = request.getParameter("cnt");

int cnt = 100;

if(s_cnt != null && !s_cnt.equals("")) {
	cnt = Integer.parseInt(s_cnt);
}
%>    
    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
	
		<% for(int i=0; i<cnt; i++) { %>
			안녕 Servlet!!<br>
		<% } %>
		
	</body>
	
</html>