package servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SignupDto;
import service.AuthService;

@WebServlet("/member/join")
public class Join extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/member/join.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		
		Date birthday = java.sql.Date.valueOf(request.getParameter("birthday"));
		
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		SignupDto signupDto = new SignupDto();
		signupDto.setUsername(username);
		signupDto.setPassword(password);
		signupDto.setName(name);
		signupDto.setGender(gender);
		signupDto.setBirthday(birthday);
		signupDto.setPhone(phone);
		signupDto.setEmail(email);
		
		AuthService authService = new AuthService();
		
		int result = authService.signup(signupDto);
		
		if(result == 0) {
			response.sendRedirect("/member/confirm");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입에 실패했습니다.\n다시 시도해주세요.'); location.href='/member/join';</script>'");
		}
		
	}

}
