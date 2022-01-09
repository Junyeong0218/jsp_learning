package service;

import domain.user.User;
import domain.user.UserDao;
import domain.user.UserDaoImpl;
import dto.SigninDto;
import dto.SignupDto;
import session.Principal;

public class AuthService {
	
	private Principal principal;
	
	public AuthService() {
		principal = Principal.getInstance();
	}
	
	public int signup(SignupDto signupDto) {
		User user = signupDto.toEntity();
		UserDao userDao = new UserDaoImpl();
		
		int result = userDao.signup(user);
		
		return result;
	}
	
	public int signin(SigninDto signinDto) {
		User user = signinDto.toEntity();
		UserDao userDao = new UserDaoImpl();
		
		int result = userDao.signinByUser(user);
		
		if(result == 2) {
			user = userDao.selectUserByUsername(signinDto.getUsername());
			principal.setLoginUser(user);
		}
		
		return result;
	}
	
	public void logout() {
		principal.setLoginUser(null);
	}

}
