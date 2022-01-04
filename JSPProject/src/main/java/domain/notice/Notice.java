package domain.notice;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notice {
	
	private int id;
	private String title;
	private String writerId;
	private String content;
	private Date regDate;
	private int hit;
	private String files;

}
