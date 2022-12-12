package repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Token;

public abstract class TokenRepository {
	
	public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("user_management_service");
	public static final EntityManager entityManager = UserRepository.entityManagerFactory.createEntityManager();
	
	public static void createSession(Token token) {
		entityManager.getTransaction().begin();
		entityManager.persist(token);
		entityManager.getTransaction().commit();
	}
	
	public static void deleteSession(String tokenCipherText) {
		entityManager.getTransaction().begin();
		TokenRepository.entityManager.createQuery("DELETE FROM Token token WHERE token.cipherText = '" + tokenCipherText + "'").executeUpdate();
		entityManager.getTransaction().commit();
	}

}
