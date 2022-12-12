package helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class HashHelper {
	
	
	public static String generateSalt() {
		return StringHelper.generateRandomString(100);
	}
	
	public static String hashPassword(String password) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger num = new BigInteger(1, messageDigest);
			String hashedPassword = num.toString(16);
			
			while(hashedPassword.length() < 32) {
				hashedPassword += "0";
			}
			
			
			return hashedPassword;
			
		}catch(NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
	public static String encyptPassword(String password, String salt) {
		return hashPassword(password + salt);
	}

}
