package domain.user;

public interface UserDao {

	public User selectUserByUsername(String username);
	public int signinByUser(User user);
}
