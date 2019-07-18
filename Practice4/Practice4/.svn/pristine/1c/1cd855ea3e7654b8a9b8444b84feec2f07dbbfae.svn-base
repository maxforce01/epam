package ua.nure.gunko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Util {

	private static final String ENCODING = "Cp1251";

	private Util() {
		throw new IllegalStateException("Utility class");
	}

	public static void writeFile(String input, String path) {
		File f = new File(path);
		try(FileOutputStream fs =new FileOutputStream(f)) {
			byte[] bytes = input.getBytes(ENCODING);
			fs.write(bytes);
			fs.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	public static String readFile(String path) {
		String res = null;
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(path));
			res = new String(bytes, ENCODING);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return res;
	}
}