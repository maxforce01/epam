package ua.nure.gunko.practice3;

import java.util.Locale;

public class Part5 {
	private static final int TWENTY = 20;
	private static final int FIVTY = 50;
	private static final int FIVTYO = 500;
	private static final int TEN = 10;
	private static final int FIVE = 5;
	private static final int ONE = 1;
	private static final int[] NUMBERS = { 10, 100, 1000, 10000 };

	public static void main(String[] args) {
		for (int i = 1; i <= TWENTY; i++) {
			System.out.println(i + " --> " + decimal2Roman(i) + " --> " + roman2Decimal(decimal2Roman(i)));
		}
	}

	public static String decimal2Roman(int i) {
		int digits = i % NUMBERS[0];
		int tens = (i % NUMBERS[1]) / NUMBERS[0];
		int hundreds = (i % NUMBERS[2]) / NUMBERS[1];
		int thousands = (i % NUMBERS[3]) / NUMBERS[2];
		return (romanLogic(thousands, "M", "", "") + romanLogic(hundreds, "C", "D", "M")
				+ romanLogic(tens, "X", "L", "C") + romanLogic(digits, "I", "V", "X"));
	}

	public static String romanLogic(int i, String ones, String fives, String tens) {
		StringBuilder sb = new StringBuilder();
		StringBuilder res = new StringBuilder();
		if (i == 0){
            return sb.toString();
        } else {
            if ((i>=4)&&(i<=8)){                
                sb.append(fives);
            }
            if (i==9){
                sb.append(tens);
            }
            if(i%5 < 4){
                while(i%5 > 0){
                    sb.append(ones);
                    i--;
                }
            }
            if(i%5 != 0 ){
                res.append(ones).append(sb);
            }else {
            	return sb.toString();
            }
        }
        return res.toString();
	}

	public static int roman2Decimal(String romanNumber) {
		int decimal = 0;
		int lastNumber = 0;
		String romanNumeral = romanNumber.toUpperCase(Locale.getDefault());
		for (int x = romanNumeral.length() - 1; x >= 0; x--) {
			char convertToDecimal = romanNumeral.charAt(x);

			switch (convertToDecimal) {
			case 'M':
				decimal = processDecimal(NUMBERS[2], lastNumber, decimal);
				lastNumber = NUMBERS[2];
				break;

			case 'D':
				decimal = processDecimal(FIVTYO, lastNumber, decimal);
				lastNumber = FIVTYO;
				break;

			case 'C':
				decimal = processDecimal(NUMBERS[1], lastNumber, decimal);
				lastNumber = NUMBERS[1];
				break;

			case 'L':
				decimal = processDecimal(FIVTY, lastNumber, decimal);
				lastNumber = FIVTY;
				break;

			case 'X':
				decimal = processDecimal(TEN, lastNumber, decimal);
				lastNumber = TEN;
				break;

			case 'V':
				decimal = processDecimal(FIVE, lastNumber, decimal);
				lastNumber = FIVE;
				break;

			case 'I':
				decimal = processDecimal(ONE, lastNumber, decimal);
				lastNumber = ONE;
				break;
			default:
				break;
			}
		}
		return decimal;
	}

	public static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
		if (lastNumber > decimal) {
			return lastDecimal - decimal;
		} else {
			return lastDecimal + decimal;
		}
	}

}
