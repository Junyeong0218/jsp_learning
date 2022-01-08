package servlet.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dto.RegNoticeDto;
import service.NoticeService;

@MultipartConfig(
	fileSizeThreshold = 1024 * 1024, // kb 단위 / 디스크에 저장할 기준단위
	maxFileSize = 1024 * 1024 * 50, // 1개의 파일 사이즈의 max 값
	maxRequestSize = 1024 * 1024 * 50 * 5 // 전체 요청에 대한 파일 사이즈
)
@WebServlet("/admin/board/notice/reg")
public class RegNotice extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String writerId = "newlecture";
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open"); // true or null
		int pub = 0;
		
		if(isOpen != null) {
			pub = 1;
		}
		
		Collection<Part> fileParts = request.getParts();
		StringBuilder sb = new StringBuilder();
		final String SEP = File.separator; 
		
		for(Part p : fileParts) {
			if(!p.getName().equals("file")) {
				continue;
			} else if(p.getSize() == 0) {
				continue;
			}
			
			Part filePart = p;
			
			String fileName = filePart.getSubmittedFileName();
			sb.append(fileName);
			sb.append(",");
			
			InputStream fis = filePart.getInputStream();
			String realPath = request.getServletContext().getRealPath("/upload" + SEP + writerId);
			
			File path = new File(realPath);
			if(!path.exists()) {
				path.mkdirs();
			}
			
			String filePath = realPath + SEP + fileName;
			// File.separator -> 운영체제에 따른 경로 구별자
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024];
			int size = 0;
			while( (size = fis.read(buf)) != (-1) ) {
				fos.write(buf, 0, size);
			}
			
			fis.close();
			fos.close();
		}
		
		sb.delete(sb.length()-1, sb.length());
		
		NoticeService noticeService = new NoticeService();
		RegNoticeDto regNoticeDto = new RegNoticeDto();
		
		regNoticeDto.setTitle(title);
		regNoticeDto.setContent(content);
		regNoticeDto.setWriterId(writerId);
		regNoticeDto.setFiles(sb.toString());
		regNoticeDto.setPub(pub);
		
		noticeService.insertNotice(regNoticeDto);
		
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
