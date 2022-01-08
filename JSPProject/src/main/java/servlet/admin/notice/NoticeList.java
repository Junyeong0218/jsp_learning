package servlet.admin.notice;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.notice.NoticeView;
import service.NoticeService;

@WebServlet("/admin/board/notice/list")
public class NoticeList extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tempList = request.getParameter("list");
		String option = request.getParameter("option");
		String keyword = request.getParameter("keyword");
		
		final boolean PUBBED = false;
		
		int list = 1;
		
		if(!isEmpty(tempList)) {
			list = Integer.parseInt(tempList);
		}
		
		NoticeService noticeService = new NoticeService();
		
		List<NoticeView> notices;
		int startNum = list - ((list - 1) % 5);
		int noticeCnt;
		
		if(!isEmpty(keyword)) {
			notices = noticeService.getNoticeViewList(option, keyword, list, PUBBED);
			noticeCnt = noticeService.getNoticeCount(option, keyword, PUBBED);
		} else {
			notices = noticeService.getNoticeViewList(list, PUBBED);
			noticeCnt = noticeService.getNoticeCount(PUBBED);
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp");
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String allIds_ = request.getParameter("allIds");
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		String btnFlag = request.getParameter("btn");
		
		StringTokenizer st = new StringTokenizer(allIds_, " ");
		String[] allIds = new String[st.countTokens()];
		for(int i=0; i<allIds.length; i++) {
			allIds[i] = st.nextToken();
		}
		
		NoticeService noticeService = new NoticeService();
		
		if(btnFlag.equals("ÀÏ°ý°ø°³")) {
			noticeService.pubNoticeAll(allIds, openIds);
		} else if(btnFlag.equals("ÀÏ°ý»èÁ¦")) {
			noticeService.deleteNoticeAll(delIds);
		}

		response.sendRedirect("list");
		
	}
	
	protected boolean isEmpty(String str) {
		if(str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
