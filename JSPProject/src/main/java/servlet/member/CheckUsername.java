package servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.user.UserDao;
import domain.user.UserDaoImpl;

@WebServlet("/member/checkusername")
public class CheckUsername extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		int result = 0;
		
		if(username.matches("^[a-zA-Z0-9]*$")) {
			UserDao userDao = new UserDaoImpl();
			result = userDao.selectUsernameByUsername(username);
		} else {
			result = 2;
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("{\"result\": " + result + "}");
		out.close();
	}
}
