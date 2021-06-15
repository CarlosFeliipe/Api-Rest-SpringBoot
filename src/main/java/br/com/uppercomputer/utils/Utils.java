package br.com.uppercomputer.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

	public static String md5(String senha) throws NoSuchAlgorithmException {
		MessageDigest messagedig = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, messagedig.digest(senha.getBytes()));
		return hash.toString(16);
	}
	
	public static String generatePassword() {
		StringBuilder senha = new StringBuilder();
		String[] caracteres = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
                "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y","z","+","-","/","*","_","!","@","$","%","&"};

	    for (int i = 0; i < 8; i++) {
	        int posicao = (int) (Math.random() * caracteres.length);
	        senha.append(caracteres[posicao]);
	    }
	    return senha.toString();
	}
	
	public static String generateCode() {
		StringBuilder senha = new StringBuilder();
		String[] caracteres = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
                "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y","z","+","-","/","*","_","!","@","$","%","&"};

	    for (int i = 0; i < 8; i++) {
	        int posicao = (int) (Math.random() * caracteres.length);
	        senha.append(caracteres[posicao]);
	    }
	    return senha.toString();
	}
}
