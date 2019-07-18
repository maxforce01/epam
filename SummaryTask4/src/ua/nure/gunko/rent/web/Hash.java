package ua.nure.gunko.rent.web;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Hash {
	private static final int[] NUMBERS = {16,32};
	public static String hash(String input) {

		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.update(input.getBytes());
		byte[] hash = digest.digest();
		BigInteger bigInt = new BigInteger(1, hash);
	    StringBuilder md5Hex = new StringBuilder(bigInt.toString(NUMBERS[0]));

	    while( md5Hex.length() < NUMBERS[1] ){
	        md5Hex.append("0").append(md5Hex);
	    }

		return md5Hex.toString().toLowerCase(Locale.getDefault());
	}
}
