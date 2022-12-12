package services;

import responses.LoginServiceResponse;

public interface IUserService {
	
	String signUp(String username, String password);
	LoginServiceResponse login(String username, String password);
	String logout(String tokenCipherText);

}
