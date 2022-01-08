package domain.notice;

import java.util.List;

public interface NoticeDao {

	public Notice getNoticeDtlById(int id);
	public Notice getIdAndTitleById(int id);
	public int getNextIdByCurrentId(int id);
	public int getPrevIdByCurrentId(int id);
	public int getNoticeCount(String option, String keyword, boolean pub);
	public List<NoticeView> getNoticesByListId(String option, String keyword, int list, boolean pub);
	public List<NoticeView> getNewestNotices();
	
	public int pubNotices(List<Notice> list);
	public int deleteNotices(int[] ids);
	
	public int insertNotice(Notice notice);
}
