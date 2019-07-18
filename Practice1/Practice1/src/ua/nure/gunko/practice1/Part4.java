package ua.nure.gunko.practice1;

public class Part4 {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        while (a != 0 && b != 0) {

            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        if (a == 0) {
            System.out.println(b);
        } else {
            System.out.println(a);
        }
    }
}

