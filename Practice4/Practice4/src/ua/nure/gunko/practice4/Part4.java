package ua.nure.gunko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {
	private String encoding = "Cp1251";

	private Matcher matcher;

	private static final String REGEXP = "\\p{javaUpperCase}.*?\\.";

	private static File file;

	public Part4(String filename) throws FileNotFoundException{
		setFile(new File(filename));
		StringBuilder sb = new StringBuilder();
		Scanner s = new Scanner(file, getEncoding());
			while (s.hasNextLine()) {
				sb.append(s.nextLine()).append(" ");
				sb.delete(sb.length() - 1, sb.length());
				Pattern p = Pattern.compile(REGEXP);
				matcher = p.matcher(sb);
			}
		s.close();
	}
	
	public void output() {
		
		while(matcher.find()) {
			System.out.println(matcher.group());
		}

	}
	public static File getFile() {
		return file;
	}

	public static void setFile(File file) {
		Part4.file = file;
	}

	public Matcher getMatcher() {
		return matcher;
	}

	public void setMatcher(Matcher matcher) {
		this.matcher = matcher;
	}

	public String getEncoding() {
		return encoding;
	}

	@Override
	public Iterator<String> iterator() {
		return new IteratorImpl(matcher);
	}

	static class IteratorImpl implements Iterator<String> {

		private Matcher matcher;

		public IteratorImpl(Matcher matcher) {
			setMatcher(matcher);
		}

		public Matcher getMatcher() {
			return this.matcher;
		}

		public final void setMatcher(Matcher matcher) {
			this.matcher = matcher;
		}
		@Override
		public boolean hasNext() {
			return getMatcher().find();
		}

		@Override
		public String next() {
			if(!hasNext()){
			      throw new NoSuchElementException();
			    }
			return getMatcher().group();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
			new Part4("part4.txt").output();
	}

}
