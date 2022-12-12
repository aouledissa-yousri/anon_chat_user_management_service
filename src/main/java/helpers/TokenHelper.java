package helpers;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;





public abstract class TokenHelper {
	
	static final String SECRET_KEY = "eF7F.&|hG\"KEuD3c0yA8e6}=zmB3O5";
	static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";

	
	public static String generateJWTtoken(int id, String username) {
		
		try {
			JSONObject payload = TokenHelper.generateTokenPayload(id, username);
			return EncodingHelper.encodeToBase64(TokenHelper.JWT_HEADER) + "." + EncodingHelper.encodeToBase64(payload.toString()) + "." + TokenHelper.generateTokenSignature(payload.toString()); 
			
		}catch(JSONException e) {
			return "Invalid payload";
			
		}catch(IllegalArgumentException e) {
			return "Invalid arguments";
		}
		
	}
	
	public static String generateTokenSignature(String payload) {
		return EncodingHelper.generateHmacSha256(EncodingHelper.encodeToBase64(TokenHelper.JWT_HEADER) + "." + EncodingHelper.encodeToBase64(payload), TokenHelper.SECRET_KEY);
	}
	
	
	public static JSONObject generateTokenPayload(int id, String username) throws JSONException {
		JSONObject payload = new JSONObject();
		payload.put("id", id);
		payload.put("username", username);
		payload.put("number", 9999999 * Math.random());
		
		return payload;
		
	}

}
