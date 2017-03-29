package br.com.sistema.util;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class GenerateHashPasswordUtil {
	private static Object salt;

	public static String generateHash(String password) {
//		MessageDigestPasswordEncoder digestPasswordEncoder = getInstanceMessageDisterPassword();
//		String encodePassword = digestPasswordEncoder.encodePassword(password, salt);
		BCryptPasswordEncoder passwEncoder = new BCryptPasswordEncoder(10);
		return passwEncoder.encode(password);
	}

	private static MessageDigestPasswordEncoder getInstanceMessageDisterPassword() {
		// informo tipo de enconding que desejo
		MessageDigestPasswordEncoder digestPasswordEncoder = new MessageDigestPasswordEncoder("MD5");
		return digestPasswordEncoder;
	}

	// método que faz a validação como não usamos salt deixei em null
	public static boolean isPasswordValid(String password, String hashPassword) {
		MessageDigestPasswordEncoder digestPasswordEncoder = getInstanceMessageDisterPassword();
		return digestPasswordEncoder.isPasswordValid(hashPassword, password, salt);
	}
}