
package ua.nure.gunko.practice6.part1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Part1 {
	private InputStream stdIn = System.in;

	public static void main(String[] args) throws IOException {
		System.setIn(new ByteArrayInputStream(
				("asd 43 asdf asd 43\r\n" + "434 asdf\r\n" + "kasdf asdf stop asdf").getBytes()));
		WordContainer.main(new String[] {});
		System.setIn(new Part1().stdIn);
	}
}
