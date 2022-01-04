package domain.notice;

import java.util.List;

public interface NoticeDao {

	public Notice getNoticeDtlById(int id);
	public List<Notice> getNoticesByListId(int id);
}
