


package ua.nure.gunko.practice6;


import java.io.IOException;

import ua.nure.gunko.practice6.part1.Part1;
import ua.nure.gunko.practice6.part2.Part2;
import ua.nure.gunko.practice6.part3.Part3;
import ua.nure.gunko.practice6.part4.Part4;
import ua.nure.gunko.practice6.part5.Part5;
import ua.nure.gunko.practice6.part6.Part6;

public class Demo {
	public static void main(String[] args) throws IOException {
		System.out.println("~~~~~~~~~~~~Part1");
		Part1.main(args);
		System.out.println("~~~~~~~~~~~~Part2");
		Part2.main(args);
		System.out.println("~~~~~~~~~~~~Part3");
		Part3.main(args);
		System.out.println();

		System.out.println("~~~~~~~~~~~~Part4");
		Part4.main(args);

		System.out.println("~~~~~~~~~~~~Part5");
		Part5.main(args);

		System.out.println("~~~~~~~~~~~~Part6");
		Part6.main(new String[] { "-i", "part6.txt", "-t", "frequency" });
		Part6.main(new String[] { "-i", "part6.txt", "-t", "length" });
		Part6.main(new String[] { "-i", "part6.txt", "-t", "duplicates" });
	}

}
