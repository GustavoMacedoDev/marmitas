package br.com.macedo.sistemas.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {


	public static String gerarBCrypt(String senha) {
		if (senha == null) {
			return senha;
		}

		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha);
	}

	public static boolean senhaValida(String senha, String senhaEncoded) {
		BCryptPasswordEncoder bPasswordEncoder = new BCryptPasswordEncoder();
		return bPasswordEncoder.matches(senha, senhaEncoded);
	}

}
