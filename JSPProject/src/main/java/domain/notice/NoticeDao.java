package domain.notice;

import java.util.List;

public interface NoticeDao {

	public Notice getNoticeDtlById(int id);
	public Notice getIdAndTitleById(int id);
	public int getNextIdByCurrentId(int id);
	public int getPrevIdByCurrentId(int id);
	public int getNoticeCount(String option, String keyword);
	public List<NoticeView> getNoticesByListId(String option, String keyword, int list);
	public List<NoticeView> getNewestNotices();
}
