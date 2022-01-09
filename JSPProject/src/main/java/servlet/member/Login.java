package servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.SigninDto;
import service.AuthService;
import session.Principal;

@WebServlet("/member/login")
public class Login extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private Principal principal = Principal.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("User") != null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 로그인 되어 있습니다.'); location.href='/index';</script>'");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/member/login.jsp");
			dispatcher.forward(request, response);
		}
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
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result == 0) {
			out.println("<script>alert('아이디가 다릅니다.'); location.href='/member/login';</script>'");
		} else if(result == 1) {
			out.println("<script>alert('비밀번호가 다릅니다.'); location.href='/member/login';</script>'");
		} else if(result == 2) {
			HttpSession session = request.getSession();
			session.setAttribute("User", principal.getLoginUser());
			out.println("<script>location.href='/index';</script>'");
		}
		
		out.close();
	}

}
