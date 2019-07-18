
package ua.nure.gunko.practice3;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Part4 {
	private static final int[] NUMBERS = {16,32};
	public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {

		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(input.getBytes());
		byte[] hash = digest.digest();
		BigInteger bigInt = new BigInteger(1, hash);
	    StringBuilder md5Hex = new StringBuilder(bigInt.toString(NUMBERS[0]));

	    while( md5Hex.length() < NUMBERS[1] ){
	        md5Hex.append("0").append(md5Hex);
	    }

		return md5Hex.toString().toUpperCase(Locale.getDefault());
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(hash("adf", "MD5"));
		System.out.println(hash("asdf", "SHA-256"));
	}

}
