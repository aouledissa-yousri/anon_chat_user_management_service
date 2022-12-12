package services;

import javax.jws.WebService;
import javax.persistence.RollbackException;
import responses.LoginServiceResponse;
import repositories.UserRepository;

@WebService(targetNamespace = "http://services/", portName = "UserServicePort", serviceName = "UserServiceService")
public class UserService implements IUserService {
	
	
	@Override
	public String signUp(String username, String password) {
		try {
			return UserRepository.signUp(username, password);
		} catch (RollbackException e) {
			return "Account with username " + username + " Already exists";
		}
	}

	@Override
	public LoginServiceResponse login(String username, String password) {
		return UserRepository.login(username, password);
	}
	
	
	@Override
	public String logout(String tokenCipherText) {
		return UserRepository.logout(tokenCipherText);
	}
	

	
	
	
	
	

}
