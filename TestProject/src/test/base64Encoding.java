package test;

import java.util.Base64;

public class base64Encoding {
	
	// basic auth
	public static void main(String[] args) {
		
		String userNameandPassword = "myUserName:myPassWord";
		String base64Encoded = Base64.getEncoder().encodeToString(userNameandPassword.getBytes());
		byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
		System.out.println(new String(decodedBytes));
	}

}
