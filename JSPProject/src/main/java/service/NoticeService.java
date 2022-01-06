package service;

import java.util.List;

import domain.notice.Notice;
import domain.notice.NoticeDao;
import domain.notice.NoticeDaoImpl;
import domain.notice.NoticeView;

public class NoticeService {
	
	private NoticeDao noticeDao;
	
	public NoticeService() {
		noticeDao = new NoticeDaoImpl();
	}
	
	public int removeNoticeAll(List<Integer> ids) {
		return 0;
	}
	
	public int pubNoticeAll(List<Integer> ids) {
		return 0;
	}
	
	public int insertNotice(Notice notice) {
		return 0;
	}
	
	public int deleteNotice(int id) {
		return 0;
	}
	
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	public List<NoticeView> getNoticeNewestList() {
		return noticeDao.getNewestNotices();
	}

	public List<NoticeView> getNoticeViewList() {
		return getNoticeViewList("", null, 1);
	}
	
	public List<NoticeView> getNoticeViewList(int list) {
		return getNoticeViewList("", null, list);
	}
	
	public List<NoticeView> getNoticeViewList(String option, String keyword, int list) {
		return noticeDao.getNoticesByListId(option, keyword, list);
	}
	
	public int getNoticeCount() {
		return getNoticeCount("", null);
	}
	
	public int getNoticeCount(String option, String keyword) {
		return noticeDao.getNoticeCount(option, keyword);
	}
	
	public Notice getNotice(int id) {
		return noticeDao.getNoticeDtlById(id);
	}
	
	public Notice getNextNotice(int id) {
		return noticeDao.getIdAndTitleById(noticeDao.getNextIdByCurrentId(id));
	}
	
	public Notice getPrevNotice(int id) {
		return noticeDao.getIdAndTitleById(noticeDao.getPrevIdByCurrentId(id));
	}
}
