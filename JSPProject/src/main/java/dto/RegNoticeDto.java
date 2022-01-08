package dto;

import domain.notice.Notice;
import lombok.Data;

@Data
public class RegNoticeDto {

	private String title;
	private String content;
	private String writerId;
	private String files;
	private int pub;
	
	public Notice toEntity() {
		return Notice.builder()
				.title(title)
				.content(content)
				.writerId(writerId)
				.files(files)
				.pub(pub)
				.build();
	}
}
