package helpers;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public abstract class EncodingHelper {
	
	public static String encodeToBase64(String text) {
		return Base64.getUrlEncoder().encodeToString(text.getBytes());
	}
	
	public static String generateHmacSha256(String data, String secret) {
	    try {

	        byte[] hash = secret.getBytes(StandardCharsets.UTF_8);
	        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
	        sha256Hmac.init(secretKey);

	        byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

	        return signedBytes.toString();
	    } catch (Exception ex) {
	       return "An error occurred";
	    }
	}

}
