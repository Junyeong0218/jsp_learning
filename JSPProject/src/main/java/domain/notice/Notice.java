package domain.notice;

import java.time.LocalDateTime;

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
	private LocalDateTime regDate;
	private int hit;
	private String files;
	private int pub;

}
