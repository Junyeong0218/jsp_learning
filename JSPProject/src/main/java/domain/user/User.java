package domain.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

	private int id;
	private String username;
	private String password;
	private String name;
	private String email;
	private LocalDateTime signupDate;
	private LocalDateTime updateDate;
	private int permission;
	
}
