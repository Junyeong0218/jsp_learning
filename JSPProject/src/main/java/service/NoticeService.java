package service;

import java.util.ArrayList;
import java.util.List;

import domain.notice.Notice;
import domain.notice.NoticeDao;
import domain.notice.NoticeDaoImpl;
import domain.notice.NoticeView;
import dto.PubNoticesDto;
import dto.RegNoticeDto;

public class NoticeService {
	
	private NoticeDao noticeDao;
	
	public NoticeService() {
		noticeDao = new NoticeDaoImpl();
	}
	
	public int deleteNoticeAll(String[] delIds) {
		int[] ids = new int[delIds.length];
		for(int i=0; i<delIds.length; i++) {
			ids[i] = Integer.parseInt(delIds[i]);
		}
		
		return noticeDao.deleteNotices(ids);
	}
	
	public int pubNoticeAll(String[] allIds, String[] openIds) {
		int[] ids = new int[allIds.length];
		int[] pub = new int[allIds.length];
		
		for(int i=0; i<allIds.length; i++) {
			ids[i] = Integer.parseInt(allIds[i]);
		}
		
		int index = 0;
		for(int i=0; i<ids.length; i++) {
			if(ids[i] == Integer.parseInt(openIds[index])) {
				pub[i] = 1;
				index++;
			} else {
				pub[i] = 0;
			}
		}
		
		List<Notice> list = new ArrayList<Notice>();
		for(int i=0; i<ids.length; i++) {
			PubNoticesDto pubNoticesDto = new PubNoticesDto();
			pubNoticesDto.setId(ids[i]);
			pubNoticesDto.setPub(pub[i]);
			list.add(pubNoticesDto.toEntity());
		}
		
		return noticeDao.pubNotices(list);
	}
	
	public int insertNotice(RegNoticeDto regNoticeDto) {
		Notice notice = regNoticeDto.toEntity();
		
		return noticeDao.insertNotice(notice);
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

	public List<NoticeView> getNoticeViewList(boolean pub) {
		return getNoticeViewList("", null, 1, pub);
	}
	
	public List<NoticeView> getNoticeViewList(int list, boolean pub) {
		return getNoticeViewList("", null, list, pub);
	}
	
	public List<NoticeView> getNoticeViewList(String option, String keyword, int list, boolean pub) {
		return noticeDao.getNoticesByListId(option, keyword, list, pub);
	}
	
	public int getNoticeCount(boolean pub) {
		return getNoticeCount("", null, pub);
	}
	
	public int getNoticeCount(String option, String keyword, boolean pub) {
		return noticeDao.getNoticeCount(option, keyword, pub);
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
