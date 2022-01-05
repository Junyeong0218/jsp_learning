package service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.notice.Notice;
import domain.notice.NoticeDao;
import domain.notice.NoticeDaoImpl;

@WebServlet("/notice/detail")
public class ReadNoticeDtl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String temp = request.getParameter("id");
		int id = 0;
		
		if(isEmpty(temp)) {
			response.sendRedirect("/notice");
		} else {
			id = Integer.parseInt(temp);
		}
		
		NoticeDao noticeDao = new NoticeDaoImpl();
		
		Notice notice = noticeDao.getNoticeDtlById(id);
		
		request.setAttribute("notice", notice);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp");
		dispatcher.forward(request, response);
	}
	
	protected boolean isEmpty(String str) {
		if(str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
