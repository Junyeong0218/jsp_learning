package servlet.admin;

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

@WebServlet("/admin/index")
public class Index extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NoticeService noticeService = new NoticeService();
		
		List<NoticeView> newestNotices = noticeService.getNoticeNewestList();
		
		request.setAttribute("newestNotices", newestNotices);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/index.jsp");
		dispatcher.forward(request, response);
	}
}
