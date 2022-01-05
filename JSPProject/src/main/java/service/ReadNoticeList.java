package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.notice.Notice;
import domain.notice.NoticeDao;
import domain.notice.NoticeDaoImpl;

@WebServlet("/notice/list")
public class ReadNoticeList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String temp = request.getParameter("list");
		int list = 0;
		
		if(isEmpty(temp)) {
			list = 1;
		} else {
			list = Integer.parseInt(temp);
		}
		
		NoticeDao noticeDao = new NoticeDaoImpl();
		
		List<Notice> notices = noticeDao.getNoticesByListId(list);
		
		request.setAttribute("notices", notices);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp");
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
