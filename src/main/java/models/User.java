package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	static int inc = 1;

	@Id
	private int id;

	private String password;

	private String salt;

	private String username;

	//bi-directional many-to-one association to Token
	@OneToMany(mappedBy="user")
	private List<Token> tokens;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setId() {
		this.id = (int)(Math.random()*(99999999-1+1)+1);   
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Token addToken(Token token) {
		getTokens().add(token);
		token.setUser(this);

		return token;
	}

	public Token removeToken(Token token) {
		getTokens().remove(token);
		token.setUser(null);

		return token;
	}
	
	public static User createUser(String username, String password, String salt) {
		User user = new User();
		user.setId();
		user.setUsername(username);
		user.setPassword(password);
		user.setSalt(salt);
		return user;
	}

}