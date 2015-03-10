package br.com.imovelhunterweb.util;


import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -479175766322284621L;

	/**
	 * converte uma String em um hasch MD5
	 * 
	 * @param senha
	 * @return md5
	 */
	public static String gerarMd5(String senha) {
		String sen = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		sen = hash.toString(16);
		return sen;
	}
}
