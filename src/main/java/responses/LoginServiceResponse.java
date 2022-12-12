package responses;

import models.User;

public class LoginServiceResponse {
	
	private String message;
	private String username;
	private String token;
	
	public LoginServiceResponse(String message, String username, String token) {
		this.message = message;
		this.username = username;
		this.token = token;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	} 
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	  
	
	@Override
	public String toString() {
		return "message: " + this.message + "\n" +
				"username: " + this.username + "\n" + 
				"token: " + this.token;
	}
	
	


}
