package br.com.sistema.util;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class GenerateHashPasswordUtil {
	private static Object salt;

	public static String generateHash(String password) {
		BCryptPasswordEncoder passwEncoder = new BCryptPasswordEncoder(10);
		return passwEncoder.encode(password);
	}

	private static MessageDigestPasswordEncoder getInstanceMessageDisterPassword() {
		MessageDigestPasswordEncoder digestPasswordEncoder = new MessageDigestPasswordEncoder("MD5");
		return digestPasswordEncoder;
	}

	public static boolean isPasswordValid(String password, String hashPassword) {
		MessageDigestPasswordEncoder digestPasswordEncoder = getInstanceMessageDisterPassword();
		return digestPasswordEncoder.isPasswordValid(hashPassword, password, salt);
	}
}