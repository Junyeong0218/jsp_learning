package servlet.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.notice.NoticeView;
import service.NoticeService;

@WebServlet("/notice/list")
public class NoticeList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tempList = request.getParameter("list");
		String option = request.getParameter("option");
		String keyword = request.getParameter("keyword");
		
		int list = 1;
		
		if(!isEmpty(tempList)) {
			list = Integer.parseInt(tempList);
		}
		
		NoticeService noticeService = new NoticeService();
		
		List<NoticeView> notices;
		int startNum = list - ((list - 1) % 5);
		int noticeCnt;
		
		if(!isEmpty(keyword)) {
			notices = noticeService.getNoticeViewList(option, keyword, list);
			noticeCnt = noticeService.getNoticeCount(option, keyword);
		} else {
			notices = noticeService.getNoticeViewList(list);
			noticeCnt = noticeService.getNoticeCount();
		}
		
		int lastNum = noticeCnt / 10;
		if(noticeCnt % 10 > 0) {
			lastNum = noticeCnt / 10 + 1;
		}
		
		request.setAttribute("notices", notices);
		request.setAttribute("list", list);
		request.setAttribute("startNum", startNum);
		request.setAttribute("lastNum", lastNum);
		request.setAttribute("option", option);
		request.setAttribute("keyword", keyword);
		
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
