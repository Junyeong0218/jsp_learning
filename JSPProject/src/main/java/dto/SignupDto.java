package dto;

import java.util.Date;

import domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupDto {

	private String username;
	private String password;
	private String name;
	private String gender;
	private Date birthday;
	private String phone;
	private String email;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.name(name)
				.gender(gender)
				.birthday(birthday)
				.phone(phone)
				.email(email)
				.build();
	}
}
