package session;

import domain.user.User;
import lombok.Data;

@Data
public class Principal {

	private User LoginUser;
	private static Principal instance = new Principal();
	
	public Principal() {
		
	}
	
	public static Principal getInstance() {
		if(instance == null) {
			instance = new Principal();
		}
		return instance;
	}
}