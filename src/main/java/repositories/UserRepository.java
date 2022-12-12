package repositories;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import helpers.HashHelper;
import helpers.TokenHelper;
import models.Token;
import models.User;
import responses.LoginServiceResponse;

public abstract class UserRepository {
	
	public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("user_management_service");
	public static final EntityManager entityManager = UserRepository.entityManagerFactory.createEntityManager();
	
	
	public static String signUp(String username, String password) throws RollbackException {
		
		String salt = HashHelper.generateSalt();
		User user = User.createUser(username, HashHelper.encyptPassword(password,salt), salt);
		
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		
		return "Account has been created successfully";
		
		
	}
	
	public static LoginServiceResponse login(String username, String password) {
		try {
			User user = new User();
			user.setId((int) UserRepository.entityManager.createQuery("SELECT user.id FROM User user WHERE user.username = '" + username + "'").getResultList().get(0));
			user.setUsername((String) UserRepository.entityManager.createQuery("SELECT user.username FROM User user WHERE user.username = '" + username + "'").getResultList().get(0));
			user.setSalt((String) UserRepository.entityManager.createQuery("SELECT user.salt FROM User user WHERE user.username = '" + username + "'").getResultList().get(0));
			
			
			try {
				user.setPassword((String) UserRepository.entityManager.createQuery("SELECT user.password FROM User user WHERE user.password = '" + HashHelper.encyptPassword(password, user.getSalt()) + "'").getResultList().get(0));
				String tokenCipherText = TokenHelper.generateJWTtoken(user.getId(), user.getUsername());
				TokenRepository.createSession(Token.createToken(tokenCipherText, user));
				
				return new LoginServiceResponse("Success", user.getUsername(), tokenCipherText);
				
			}catch(ArrayIndexOutOfBoundsException ex) {
				return new LoginServiceResponse("Password is wrong", "", "");
				
			}
			
			
		}catch(ArrayIndexOutOfBoundsException ex) {
			return new LoginServiceResponse("User not found", "", "");
		}
	}
	
	public static String logout(String tokenCipherText) {
		TokenRepository.deleteSession(tokenCipherText);
		return "Logged out";
	}

}
