package helpers;

public abstract class StringHelper {
	
	
	public static String generateRandomString(int length) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 abcdefghijklmnopqrstuvxyz_ç&éœ#'(-)àç'è(ç=";
		String result = "";
		
		for(int i = 0; i < length; i++) {
			int index = (int)(alphaNumericString.length() * Math.random());
			result += alphaNumericString.charAt(index);
		}
		
		return result;
		
	}

}
