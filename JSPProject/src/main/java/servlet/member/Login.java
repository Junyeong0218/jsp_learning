package servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SigninDto;
import service.AuthService;
import session.Principal;

@WebServlet("/member/login")
public class Login extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private Principal principal = Principal.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/member/login.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		SigninDto signinDto = new SigninDto();
		signinDto.setUsername(username);
		signinDto.setPassword(password);
		
		AuthService authService = new AuthService();
		int result = authService.signin(signinDto);
		
		PrintWriter out = response.getWriter();
		
		if(result == 0) {
			out.println("<script>alert('아이디가 다릅니다.');</script>'");
		} else if(result == 1) {
			out.println("<script>alert('비밀번호가 다릅니다.');</script>'");
		} else if(result == 2) {
			out.println("<script>alert(" + principal.getLoginUser().getName() +"'님 환영합니다.');</script>'");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		dispatcher.forward(request, response);
	}

}
