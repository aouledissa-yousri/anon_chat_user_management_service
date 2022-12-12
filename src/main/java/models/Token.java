package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the token database table.
 * 
 */
@Entity
@Table(name="token")
@NamedQuery(name="Token.findAll", query="SELECT t FROM Token t")
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;
	static int inc = 1;

	@Id
	private int id;

	private String cipherText;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public Token() {
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

	public String getCipherText() {
		return this.cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public static Token createToken(String cipherText, User user) {
		Token token = new Token();
		token.setId();
		token.setCipherText(cipherText);
		token.setUser(user);
		return token;
	}

}